package com.somcom.cart.router;

import com.somcom.cart.handler.OrderHandler;
import com.somcom.cart.handler.PaymentHandler;
import com.somcom.cart.handler.ProductHandler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import com.somcom.cart.web.ResponseHelper;

public final class AppRoutes {
    private AppRoutes() {
    }

    public static void register(
        Router router,
        String paymentProvider,
        ProductHandler productHandler,
        OrderHandler orderHandler,
        PaymentHandler paymentHandler
    ) {
        router.get("/health").handler(ctx -> {
            JsonObject response = new JsonObject()
                .put("ok", true)
                .put("paymentProvider", paymentProvider);
            ResponseHelper.ok(ctx, response);
        });

        router.get("/api/products").handler(productHandler::getProducts);

        router.post("/api/orders").handler(orderHandler::createOrder);
        router.get("/api/orders/:orderId").handler(orderHandler::getOrder);

        router.post("/api/payments/checkout").handler(paymentHandler::createCheckout);
        router.get("/api/payments/mock-gateway/:paymentId").handler(paymentHandler::renderMockGatewayPage);
        router.get("/api/payments/callback").handler(paymentHandler::handleCallback);
        router.post("/api/payments/webhook").handler(paymentHandler::handleWebhook);
    }
}
