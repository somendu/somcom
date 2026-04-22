package com.somcom.cart;

import java.util.Locale;

public record AppConfig(
    int port,
    String appBaseUrl,
    String frontendBaseUrl,
    String frontendSuccessPath,
    String frontendCancelPath,
    String defaultCurrency,
    String paymentProvider
) {
    public static AppConfig fromEnvironment() {
        return new AppConfig(
            parsePort(getEnv("PORT", "8080")),
            trimTrailingSlash(getEnv("APP_BASE_URL", "http://localhost:8080")),
            trimTrailingSlash(getEnv("FRONTEND_BASE_URL", "http://localhost:3000")),
            getEnv("FRONTEND_SUCCESS_PATH", "/order-success"),
            getEnv("FRONTEND_CANCEL_PATH", "/checkout"),
            getEnv("DEFAULT_CURRENCY", "INR").toUpperCase(Locale.ROOT),
            getEnv("PAYMENT_PROVIDER", "mock").toLowerCase(Locale.ROOT)
        );
    }

    private static String getEnv(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }

    private static String trimTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    private static int parsePort(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 8080;
        }
    }
}
