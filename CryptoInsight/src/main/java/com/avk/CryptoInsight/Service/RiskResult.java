package com.avk.CryptoInsight.Service;

public record RiskResult(String level,
                         String color,
                         String liquidityStatus,
                         String volumeStatus,
                         String priceTrend,
                         String rugPullRisk
) {

}
