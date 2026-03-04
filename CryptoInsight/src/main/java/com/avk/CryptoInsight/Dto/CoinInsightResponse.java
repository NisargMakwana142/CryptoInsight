package com.avk.CryptoInsight.Dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoinInsightResponse {

    private String name;
    private String symbol;
    private String imageUrl;
    private double price;
    private double liquidity;
    private double volume24h;
    private double marketCap;
    private String riskLevel;
    private String riskColor;
    private AnalysisResponse analysis;

}
