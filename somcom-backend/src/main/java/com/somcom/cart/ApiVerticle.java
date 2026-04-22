package com.somcom.cart;

import com.somcom.cart.handler.OrderHandler;
import com.somcom.cart.handler.PaymentHandler;
import com.somcom.cart.handler.ProductHandler;
import com.somcom.cart.repository.InMemoryOrderRepository;
import com.somcom.cart.repository.InMemoryPaymentRepository;
import com.somcom.cart.router.AppRoutes;
import com.somcom.cart.service.OrderService;
import com.somcom.cart.service.PaymentService;
import com.somcom.cart.web.ResponseHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ApiVerticle extends AbstractVerticle {
    private final AppConfig config;

    public ApiVerticle(AppConfig config) {
        this.config = config;
    }

    @Override
    public void start(Promise<Void> startPromise) {
        var orderRepository = new InMemoryOrderRepository();
        var paymentRepository = new InMemoryPaymentRepository();
        var orderService = new OrderService(orderRepository, config);
        var paymentService = new PaymentService(paymentRepository, orderService, config);

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        AppRoutes.register(
            router,
            config.paymentProvider(),
            new ProductHandler(orderService),
            new OrderHandler(orderService),
            new PaymentHandler(paymentService)
        );

        router.errorHandler(500, ctx -> {
            Throwable failure = ctx.failure();
            String message = failure == null ? "Internal server error." : failure.getMessage();
            ResponseHelper.error(ctx, 500, message);
        });

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(config.port())
            .onSuccess(server -> startPromise.complete())
            .onFailure(startPromise::fail);
    }
}
