package com.somcom.cart.service;

import com.somcom.cart.AppConfig;
import com.somcom.cart.exception.ApiException;
import com.somcom.cart.repository.InMemoryOrderRepository;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {
    private final AppConfig config = new AppConfig(
        8080,
        "http://localhost:8080",
        "http://localhost:3000",
        "/order-success",
        "/checkout",
        "INR",
        "mock"
    );

    @Test
    void createsOrderWhenAmountMatchesBackendPrice() {
        OrderService orderService = new OrderService(new InMemoryOrderRepository(), config);

        JsonObject order = orderService.createOrder(new JsonObject()
            .put("productId", "fashion-hoodie-001")
            .put("productSize", "M")
            .put("productQuantity", 2)
            .put("productAmount", 259800)
            .put("currency", "INR"));

        assertEquals("PENDING_PAYMENT", order.getString("orderStatus"));
        assertEquals(259800, order.getInteger("productAmount"));
    }

    @Test
    void rejectsMismatchedAmount() {
        OrderService orderService = new OrderService(new InMemoryOrderRepository(), config);

        assertThrows(ApiException.class, () -> orderService.createOrder(new JsonObject()
            .put("productId", "fashion-hoodie-001")
            .put("productSize", "M")
            .put("productQuantity", 2)
            .put("productAmount", 100)
            .put("currency", "INR")));
    }
}
