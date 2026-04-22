package com.somcom.cart;

import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        AppConfig config = AppConfig.fromEnvironment();
        vertx.deployVerticle(new ApiVerticle(config))
            .onSuccess(id -> System.out.println("Shopping cart backend started with deployment id " + id))
            .onFailure(error -> {
                error.printStackTrace();
                vertx.close();
            });

        // Deploy later when background work is introduced and should stay inside
        // this service boundary without becoming a separate microservice.
        // vertx.deployVerticle(new OptionalWorkerVerticle());
    }
}
