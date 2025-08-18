package org.example.core;

/**
 * Utility class for string operations.
 */
public class StringTools {
    public static String reverse(String in) { return new StringBuilder(in).reverse().toString(); }
    public static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}