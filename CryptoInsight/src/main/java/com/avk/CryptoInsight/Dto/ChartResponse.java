package com.avk.CryptoInsight.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartResponse {
	
	private String symbol;
    private String chain;
    private String pairId;

    private double price;
    private double priceChange24h;
    private double volume24h;
	
}
