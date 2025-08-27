package com.example.langfeatures.records;

public record Book(String title, String author, int year) {
    public Book {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        if (year < 1450) {
            throw new IllegalArgumentException("Year must be after the invention of the printing press.");
        }
    }
    public String getCitation() {
        return String.format("\"%s\" by %s (%d)", title, author, year);
    }
    public static Book createModern(String title, String author) {
        return new Book(title, author, java.time.Year.now().getValue());
    }
}
