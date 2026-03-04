package com.avk.CryptoInsight.Client;


import com.avk.CryptoInsight.Dto.DexPairResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DexScreenerClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public DexScreenerClient(RestTemplate restTemplate, @Value("${dexscreener.base-url}") String baseUrl) {
        this.restTemplate=restTemplate;
        this.baseUrl=baseUrl;
    }

    public DexPairResponse fetchPair(String chain, String pairId) {
        String url = baseUrl + "/dex/pairs/" + chain + "/" + pairId;
        return restTemplate.getForObject(url, DexPairResponse.class);
    }


}
