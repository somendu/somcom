package com.somcom.cart.service;

import com.somcom.cart.AppConfig;
import com.somcom.cart.exception.ApiException;
import com.somcom.cart.model.Product;
import com.somcom.cart.model.ProductCatalog;
import com.somcom.cart.repository.InMemoryOrderRepository;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class OrderService {
    private final InMemoryOrderRepository orderRepository;
    private final AppConfig config;

    public OrderService(InMemoryOrderRepository orderRepository, AppConfig config) {
        this.orderRepository = orderRepository;
        this.config = config;
    }

    public JsonArray getProducts() {
        JsonArray array = new JsonArray();
        for (Product product : ProductCatalog.products()) {
            array.add(product.toJson());
        }
        return array;
    }

    public JsonObject createOrder(JsonObject payload) {
        if (payload == null) {
            throw new ApiException(400, "Request body is required.");
        }

        String productId = requiredString(payload, "productId");
        String productSize = requiredString(payload, "productSize");
        int productQuantity = requiredPositiveInteger(payload, "productQuantity");
        int productAmount = requiredPositiveInteger(payload, "productAmount");
        String currency = payload.getString("currency", config.defaultCurrency()).toUpperCase(Locale.ROOT);

        Product product = findProduct(productId);
        if (product == null) {
            throw new ApiException(404, "Product not found.");
        }

        if (!product.sizes().contains(productSize)) {
            throw new ApiException(400, "Selected size is not available for this product.");
        }

        if (!product.currency().equals(currency)) {
            throw new ApiException(400, "Currency mismatch. Expected " + product.currency() + ".");
        }

        int expectedAmount = product.unitAmount() * productQuantity;
        if (productAmount != expectedAmount) {
            throw new ApiException(400, "Amount mismatch. Expected " + expectedAmount + ".");
        }

        String now = Instant.now().toString();
        JsonObject order = new JsonObject()
            .put("id", "order_" + UUID.randomUUID())
            .put("productId", product.id())
            .put("productName", product.name())
            .put("articleCode", product.articleCode())
            .put("productSize", productSize)
            .put("productQuantity", productQuantity)
            .put("unitAmount", product.unitAmount())
            .put("productAmount", productAmount)
            .put("currency", currency)
            .put("orderStatus", "PENDING_PAYMENT")
            .put("paymentStatus", "PENDING")
            .put("createdAt", now)
            .put("updatedAt", now);

        orderRepository.save(order);
        return order.copy();
    }

    public JsonObject getOrder(String orderId) {
        JsonObject order = orderRepository.findById(orderId);
        if (order == null) {
            throw new ApiException(404, "Order not found.");
        }
        return order;
    }

    public JsonObject markOrderPaid(String orderId, String paymentId) {
        JsonObject order = getOrder(orderId)
            .put("paymentId", paymentId)
            .put("orderStatus", "ORDERED")
            .put("paymentStatus", "PAID")
            .put("updatedAt", Instant.now().toString());
        orderRepository.save(order);
        return order.copy();
    }

    public JsonObject markOrderCancelled(String orderId, String paymentId) {
        JsonObject order = getOrder(orderId)
            .put("paymentId", paymentId)
            .put("orderStatus", "PAYMENT_CANCELLED")
            .put("paymentStatus", "CANCELLED")
            .put("updatedAt", Instant.now().toString());
        orderRepository.save(order);
        return order.copy();
    }

    public JsonObject markOrderPaymentFailed(String orderId, String paymentId) {
        JsonObject order = getOrder(orderId)
            .put("paymentId", paymentId)
            .put("orderStatus", "PAYMENT_FAILED")
            .put("paymentStatus", "FAILED")
            .put("updatedAt", Instant.now().toString());
        orderRepository.save(order);
        return order.copy();
    }

    private Product findProduct(String productId) {
        List<Product> products = ProductCatalog.products();
        for (Product product : products) {
            if (product.id().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private String requiredString(JsonObject payload, String fieldName) {
        String value = payload.getString(fieldName);
        if (value == null || value.isBlank()) {
            throw new ApiException(400, fieldName + " is required.");
        }
        return value;
    }

    private int requiredPositiveInteger(JsonObject payload, String fieldName) {
        Integer value = payload.getInteger(fieldName);
        if (value == null || value <= 0) {
            throw new ApiException(400, fieldName + " must be a positive integer.");
        }
        return value;
    }
}
