package com.somcom.cart.repository;

import io.vertx.core.json.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOrderRepository {
    private final Map<String, JsonObject> storage = new ConcurrentHashMap<>();

    public JsonObject save(JsonObject order) {
        storage.put(order.getString("id"), order.copy());
        return order;
    }

    public JsonObject findById(String id) {
        JsonObject order = storage.get(id);
        return order == null ? null : order.copy();
    }
}
