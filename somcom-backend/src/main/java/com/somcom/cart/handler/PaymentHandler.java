package com.somcom.cart.handler;

import com.somcom.cart.exception.ApiException;
import com.somcom.cart.service.PaymentService;
import com.somcom.cart.web.ResponseHelper;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class PaymentHandler {
    private final PaymentService paymentService;

    public PaymentHandler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void createCheckout(RoutingContext ctx) {
        try {
            JsonObject body = ctx.body().asJsonObject();
            JsonObject payment = paymentService.createCheckout(body);
            ResponseHelper.created(ctx, payment);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to create checkout.");
        }
    }

    public void renderMockGatewayPage(RoutingContext ctx) {
        try {
            String html = paymentService.renderMockGatewayPage(ctx.pathParam("paymentId"));
            ResponseHelper.html(ctx, html);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to render payment page.");
        }
    }

    public void handleCallback(RoutingContext ctx) {
        try {
            String redirectUrl = paymentService.handleCallback(ctx.request().params());
            ResponseHelper.redirect(ctx, redirectUrl);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to handle callback.");
        }
    }

    public void handleWebhook(RoutingContext ctx) {
        try {
            JsonObject body = ctx.body().asJsonObject();
            JsonObject response = paymentService.handleWebhook(body);
            ResponseHelper.ok(ctx, response);
        } catch (ApiException ex) {
            respondWithError(ctx, ex.statusCode(), ex.getMessage());
        } catch (Exception ex) {
            respondWithError(ctx, 500, "Failed to handle webhook.");
        }
    }

    private void respondWithError(RoutingContext ctx, int statusCode, String message) {
        ResponseHelper.error(ctx, statusCode, message);
    }
}
