package com.avk.CryptoInsight.Service;


import com.avk.CryptoInsight.Dto.DexPairResponse;
import org.springframework.stereotype.Service;

@Service
public class RiskCalculationService {

    public RiskResult calculate(DexPairResponse.Pair pair){

        double liquidity = pair.getLiquidity().getUsd();
        double volume = pair.getVolume().getH24();
        double priceChange24h = pair.getPriceChange().getH24();

        String riskLevel = "SAFE";
        String color = "GREEN";

        if (liquidity < 50_000 || volume < 10_000 || priceChange24h < -40) {
            riskLevel = "HIGH RISK";
            color = "RED";
        } else if (liquidity < 150_000) {
            riskLevel = "MODERATE";
            color = "YELLOW";
        }

        return new RiskResult(
                riskLevel,
                color,
                liquidity >= 150_000 ? "Good" : "Low",
                volume >= 100_000 ? "High" : "Low",
                priceChange24h > 0 ? "Bullish" : "Bearish",
                liquidity < 50_000 ? "High" : "Low"
        );

    }

}
