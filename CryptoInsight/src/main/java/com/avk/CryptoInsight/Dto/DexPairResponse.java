package com.avk.CryptoInsight.Dto;



import lombok.Data;


import java.util.List;

@Data
public class DexPairResponse {

    private List<Pair> pairs;

    @Data
    public static class Pair {
        private BaseToken baseToken;
        private double priceUsd;
        private Liquidity liquidity;
        private Volume volume;
        private PriceChange priceChange;
        private double marketCap;
        private Info info;
    }

    @Data
    public static class BaseToken {
        private String name;
        private String symbol;
    }
    
    @Data
    public static class Info {
    	private String imageUrl;
    }

    @Data
    public static class Liquidity {
        private double usd;
    }

    @Data
    public static class Volume {
        private double h24;
    }

    @Data
    public static class PriceChange {
        private double h24;
    }

}
