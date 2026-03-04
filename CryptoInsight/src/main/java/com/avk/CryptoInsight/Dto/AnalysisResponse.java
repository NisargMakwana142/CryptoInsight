package com.avk.CryptoInsight.Dto;

public record AnalysisResponse(String liquidityStatus,
                               String volumeStatus,
                               String priceTrend,
                               String rugPullRisk
) { }
