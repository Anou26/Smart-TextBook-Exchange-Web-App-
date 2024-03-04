package edu.syr.textbooks.util;

public class PriceCalculator {
    // 10% depreciation Logic for every transaction
    public static double calculateDepreciatedPrice(double currentPrice) {
        return Math.max(currentPrice * 0.9, 2.0);         // Threshold price of $2.0 which user must pay.
    }
}
