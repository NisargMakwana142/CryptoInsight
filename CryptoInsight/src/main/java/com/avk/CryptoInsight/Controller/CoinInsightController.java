package com.avk.CryptoInsight.Controller;


import com.avk.CryptoInsight.Dto.ChartResponse;
import com.avk.CryptoInsight.Dto.CoinInsightResponse;
import com.avk.CryptoInsight.Service.CoinInsightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coins")
public class CoinInsightController {

    private final CoinInsightService service;

    public CoinInsightController(CoinInsightService service){
        this.service=service;
    }

    @GetMapping("/{chain}/{pairId}/insight")
    public CoinInsightResponse getInSight(
            @PathVariable String chain,
            @PathVariable String pairId
    ){
        return service.getInsight(chain,pairId);
    }
    
    @GetMapping("/{chain}/{pairId}/chart")
    public ChartResponse getChart(
            @PathVariable String chain,
            @PathVariable String pairId
    ){
        return service.getChart(chain, pairId);
    }

}
