package com.somcom.cart.handler;

import com.somcom.cart.exception.ApiException;
import com.somcom.cart.service.OrderService;
import com.somcom.cart.web.ResponseHelper;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class OrderHandler {
    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrder(RoutingContext ctx) {
        try {
            JsonObject body = ctx.body().asJsonObject();
            JsonObject order = orderService.createOrder(body);
            ResponseHelper.created(ctx, order);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to create order.");
        }
    }

    public void getOrder(RoutingContext ctx) {
        try {
            JsonObject order = orderService.getOrder(ctx.pathParam("orderId"));
            ResponseHelper.ok(ctx, order);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to fetch order.");
        }
    }

    private void respondWithError(RoutingContext ctx, int statusCode, String message) {
        ResponseHelper.error(ctx, statusCode, message);
    }
}
