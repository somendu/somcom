package com.somcom.cart.model;

import java.util.List;

public final class ProductCatalog {
    private static final List<Product> PRODUCTS = List.of(
        new Product(
            "fashion-hoodie-001",
            "Urban Comfort Hoodie",
            "fashion",
            "UCH-001",
            "Heavyweight cotton hoodie with a relaxed streetwear fit.",
            List.of("S", "M", "L", "XL"),
            "INR",
            129900,
            "https://example.com/images/urban-comfort-hoodie.jpg"
        ),
        new Product(
            "fashion-jacket-002",
            "Tailored Denim Jacket",
            "fashion",
            "TDJ-002",
            "Structured denim jacket for elevated casual wear.",
            List.of("M", "L", "XL"),
            "INR",
            219900,
            "https://example.com/images/tailored-denim-jacket.jpg"
        ),
        new Product(
            "fashion-kurta-003",
            "Classic Linen Kurta",
            "fashion",
            "CLK-003",
            "Breathable linen kurta designed for festive and daily wear.",
            List.of("S", "M", "L", "XL", "XXL"),
            "INR",
            159900,
            "https://example.com/images/classic-linen-kurta.jpg"
        )
    );

    private ProductCatalog() {
    }

    public static List<Product> products() {
        return PRODUCTS;
    }
}
