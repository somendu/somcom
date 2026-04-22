package com.somcom.cart.model;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.List;

public record Product(
    String id,
    String name,
    String category,
    String articleCode,
    String description,
    List<String> sizes,
    String currency,
    int unitAmount,
    String imageUrl
) {
    public JsonObject toJson() {
        return new JsonObject()
            .put("id", id)
            .put("name", name)
            .put("category", category)
            .put("articleCode", articleCode)
            .put("description", description)
            .put("sizes", new JsonArray(sizes))
            .put("currency", currency)
            .put("unitAmount", unitAmount)
            .put("imageUrl", imageUrl);
    }
}
