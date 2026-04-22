package com.somcom.cart.service;

import com.somcom.cart.AppConfig;
import com.somcom.cart.exception.ApiException;
import com.somcom.cart.repository.InMemoryPaymentRepository;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Locale;
import java.util.UUID;

public class PaymentService {
    private final InMemoryPaymentRepository paymentRepository;
    private final OrderService orderService;
    private final AppConfig config;

    public PaymentService(InMemoryPaymentRepository paymentRepository, OrderService orderService, AppConfig config) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.config = config;
    }

    public JsonObject createCheckout(JsonObject payload) {
        if (payload == null) {
            throw new ApiException(400, "Request body is required.");
        }

        String orderId = payload.getString("orderId");
        if (orderId == null || orderId.isBlank()) {
            throw new ApiException(400, "orderId is required.");
        }

        JsonObject order = orderService.getOrder(orderId);
        if ("PAID".equals(order.getString("paymentStatus"))) {
            throw new ApiException(409, "Order has already been paid.");
        }

        String paymentId = "payment_" + UUID.randomUUID();
        String now = Instant.now().toString();

        JsonObject payment = new JsonObject()
            .put("id", paymentId)
            .put("orderId", orderId)
            .put("amount", order.getInteger("productAmount"))
            .put("currency", order.getString("currency"))
            .put("provider", config.paymentProvider())
            .put("status", "PENDING")
            .put("createdAt", now)
            .put("updatedAt", now);

        String paymentUrl = config.appBaseUrl() + "/api/payments/mock-gateway/" + paymentId;
        payment.put("paymentUrl", paymentUrl);
        paymentRepository.save(payment);

        return new JsonObject()
            .put("orderId", orderId)
            .put("paymentId", paymentId)
            .put("paymentUrl", paymentUrl)
            .put("provider", config.paymentProvider())
            .put("orderStatus", order.getString("orderStatus"))
            .put("paymentStatus", payment.getString("status"));
    }

    public String renderMockGatewayPage(String paymentId) {
        if (!"mock".equals(config.paymentProvider())) {
            throw new ApiException(404, "Mock gateway page is only available when PAYMENT_PROVIDER=mock.");
        }

        JsonObject payment = getPayment(paymentId);
        return """
            <!DOCTYPE html>
            <html lang="en">
              <head>
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <title>Mock Payment Gateway</title>
                <style>
                  body { font-family: Arial, sans-serif; background: #f7f3ee; color: #1f2937; padding: 40px; }
                  .card { max-width: 560px; margin: 0 auto; background: white; border-radius: 16px; padding: 32px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); }
                  .actions { display: flex; gap: 12px; margin-top: 24px; flex-wrap: wrap; }
                  a { text-decoration: none; padding: 12px 18px; border-radius: 10px; font-weight: 700; color: white; }
                  .success { background: #14532d; }
                  .cancel { background: #b91c1c; }
                  .fail { background: #374151; }
                </style>
              </head>
              <body>
                <div class="card">
                  <h1>Mock Gateway</h1>
                  <p>This page simulates a hosted payment provider for local and demo testing.</p>
                  <p><strong>Payment ID:</strong> %s</p>
                  <p><strong>Order ID:</strong> %s</p>
                  <div class="actions">
                    <a class="success" href="/api/payments/callback?paymentId=%s&status=success">Pay Successfully</a>
                    <a class="cancel" href="/api/payments/callback?paymentId=%s&status=cancel">Cancel Payment</a>
                    <a class="fail" href="/api/payments/callback?paymentId=%s&status=failed">Fail Payment</a>
                  </div>
                </div>
              </body>
            </html>
            """.formatted(
            paymentId,
            payment.getString("orderId"),
            paymentId,
            paymentId,
            paymentId
        );
    }

    public String handleCallback(MultiMap params) {
        String paymentId = params.get("paymentId");
        String status = params.get("status");

        if (paymentId == null || paymentId.isBlank() || status == null || status.isBlank()) {
            throw new ApiException(400, "paymentId and status are required.");
        }

        JsonObject payment = getPayment(paymentId);
        String frontendStatus = status.toLowerCase(Locale.ROOT);

        if ("success".equals(frontendStatus)) {
            payment = markPaymentSucceeded(paymentId);
        } else if ("cancel".equals(frontendStatus)) {
            payment = markPaymentCancelled(paymentId);
        } else {
            payment = markPaymentFailed(paymentId);
            frontendStatus = "failed";
        }

        String frontendPath = "success".equals(frontendStatus)
            ? config.frontendSuccessPath()
            : config.frontendCancelPath();

        return config.frontendBaseUrl()
            + frontendPath
            + "?orderId=" + encode(payment.getString("orderId"))
            + "&paymentId=" + encode(paymentId)
            + "&status=" + encode(frontendStatus);
    }

    public JsonObject handleWebhook(JsonObject payload) {
        if (payload == null) {
            throw new ApiException(400, "Webhook body is required.");
        }

        String eventType = payload.getString("eventType");
        String paymentId = payload.getString("paymentId");

        if (eventType == null || paymentId == null) {
            throw new ApiException(400, "eventType and paymentId are required.");
        }

        switch (eventType) {
            case "payment.succeeded" -> markPaymentSucceeded(paymentId);
            case "payment.cancelled" -> markPaymentCancelled(paymentId);
            case "payment.failed" -> markPaymentFailed(paymentId);
            default -> throw new ApiException(400, "Unsupported webhook event.");
        }

        return new JsonObject().put("received", true);
    }

    private JsonObject markPaymentSucceeded(String paymentId) {
        JsonObject payment = getPayment(paymentId)
            .put("status", "SUCCEEDED")
            .put("updatedAt", Instant.now().toString());
        paymentRepository.save(payment);
        orderService.markOrderPaid(payment.getString("orderId"), paymentId);
        return payment.copy();
    }

    private JsonObject markPaymentCancelled(String paymentId) {
        JsonObject payment = getPayment(paymentId)
            .put("status", "CANCELLED")
            .put("updatedAt", Instant.now().toString());
        paymentRepository.save(payment);
        orderService.markOrderCancelled(payment.getString("orderId"), paymentId);
        return payment.copy();
    }

    private JsonObject markPaymentFailed(String paymentId) {
        JsonObject payment = getPayment(paymentId)
            .put("status", "FAILED")
            .put("updatedAt", Instant.now().toString());
        paymentRepository.save(payment);
        orderService.markOrderPaymentFailed(payment.getString("orderId"), paymentId);
        return payment.copy();
    }

    private JsonObject getPayment(String paymentId) {
        JsonObject payment = paymentRepository.findById(paymentId);
        if (payment == null) {
            throw new ApiException(404, "Payment not found.");
        }
        return payment;
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
