package com.avk.CryptoInsight.Service;


import com.avk.CryptoInsight.Client.DexScreenerClient;
import com.avk.CryptoInsight.Dto.AnalysisResponse;
import com.avk.CryptoInsight.Dto.ChartResponse;
import com.avk.CryptoInsight.Dto.CoinInsightResponse;
import com.avk.CryptoInsight.Dto.DexPairResponse;
import org.springframework.stereotype.Service;

@Service
public class CoinInsightService {

    private final DexScreenerClient client;
    private final RiskCalculationService riskService;

    public CoinInsightService(DexScreenerClient client, RiskCalculationService riskService){
        this.client=client;
        this.riskService=riskService;
    }

    public CoinInsightResponse getInsight(String chain, String pairId){

        DexPairResponse dexResponse = client.fetchPair(chain,pairId);
        DexPairResponse.Pair pair = dexResponse.getPairs().get(0);

        RiskResult risk = riskService.calculate(pair);

        return CoinInsightResponse.builder()
                .name(pair.getBaseToken().getName())
                .symbol(pair.getBaseToken().getSymbol())
                .imageUrl(
                		pair.getInfo() != null ? pair.getInfo().getImageUrl() : null
                		)
                .price(pair.getPriceUsd())
                .liquidity(pair.getLiquidity().getUsd())
                .volume24h(pair.getVolume().getH24())
                .marketCap(pair.getMarketCap())
                .riskLevel(risk.level())
                .riskColor(risk.color())
                .analysis(new AnalysisResponse(
                        risk.liquidityStatus(),
                        risk.volumeStatus(),
                        risk.priceTrend(),
                        risk.rugPullRisk()
                ))
                .build();
    }
    
    
    public ChartResponse getChart(String chain, String pairId){

        DexPairResponse dexResponse = client.fetchPair(chain, pairId);
        DexPairResponse.Pair pair = dexResponse.getPairs().get(0);

        return ChartResponse.builder()
                .symbol(pair.getBaseToken().getSymbol())
                .chain(chain)
                .pairId(pairId)
                .price(pair.getPriceUsd())
                .priceChange24h(pair.getPriceChange().getH24())
                .volume24h(pair.getVolume().getH24())
                .build();
    }

}
