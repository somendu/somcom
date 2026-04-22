package com.somcom.cart.repository;

import io.vertx.core.json.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPaymentRepository {
    private final Map<String, JsonObject> storage = new ConcurrentHashMap<>();

    public JsonObject save(JsonObject payment) {
        storage.put(payment.getString("id"), payment.copy());
        return payment;
    }

    public JsonObject findById(String id) {
        JsonObject payment = storage.get(id);
        return payment == null ? null : payment.copy();
    }
}
