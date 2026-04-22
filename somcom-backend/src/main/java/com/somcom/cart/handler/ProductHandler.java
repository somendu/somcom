package com.somcom.cart.handler;

import com.somcom.cart.service.OrderService;
import com.somcom.cart.web.ResponseHelper;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ProductHandler {
    private final OrderService orderService;

    public ProductHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public void getProducts(RoutingContext ctx) {
        JsonObject response = new JsonObject()
            .put("products", orderService.getProducts());
        ResponseHelper.ok(ctx, response);
    }
}
