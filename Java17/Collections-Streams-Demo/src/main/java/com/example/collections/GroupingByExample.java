package com.example.collections;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {

    record Transaction(String currency, double amount, String toCurrency, double convertedAmount) {}

    public static void demonstrate() {
        System.out.println("\n--- 3. Collectors.groupingBy() ---");
        List<Transaction> transactions = List.of(
                new Transaction("USD", 100.0, "EUR", 85.0),
                new Transaction("EUR", 150.0, "INR", 13000.0),
                new Transaction("USD", 220.0," JPY", 29000.0),
                new Transaction("JPY", 8000.0, "USD", 70.0),
                new Transaction("EUR", 75.0, "INR", 6500.0)
        );

        // Group transactions by currency and sum the amounts.
        Map<String, Double> totalByCurrency = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::currency,
                        Collectors.summingDouble(Transaction::amount)
                ));

        // Group transactions by currency and toCurrency, and sum the amounts.
        Map<String, Map<String, Double>> totalByCurrencyAndToCurrency = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::currency,
                        Collectors.groupingBy(
                                Transaction::toCurrency,
                                Collectors.summingDouble(Transaction::amount)
                        )
                ));

        System.out.println("Total transaction amount by currency: " + totalByCurrency);
        System.out.println("Total transaction amount by currency and toCurrency: " + totalByCurrencyAndToCurrency);
    }
}
