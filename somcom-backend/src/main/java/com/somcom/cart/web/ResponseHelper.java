package com.somcom.cart.web;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public final class ResponseHelper {
    private ResponseHelper() {
    }

    public static void ok(RoutingContext ctx, JsonObject body) {
        ctx.response()
            .putHeader("content-type", "application/json")
            .end(body.encode());
    }

    public static void created(RoutingContext ctx, JsonObject body) {
        ctx.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json")
            .end(body.encode());
    }

    public static void error(RoutingContext ctx, int statusCode, String message) {
        ctx.response()
            .setStatusCode(statusCode)
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("message", message).encode());
    }

    public static void html(RoutingContext ctx, String body) {
        ctx.response()
            .putHeader("content-type", "text/html; charset=utf-8")
            .end(body);
    }

    public static void redirect(RoutingContext ctx, String location) {
        ctx.response()
            .setStatusCode(302)
            .putHeader("location", location)
            .end();
    }
}
