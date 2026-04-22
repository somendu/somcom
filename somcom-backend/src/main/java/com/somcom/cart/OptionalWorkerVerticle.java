package com.somcom.cart;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class OptionalWorkerVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        // Keep this verticle available for future background tasks that can live
        // in the same deployable service without introducing inter-service calls.
        startPromise.complete();
    }
}
