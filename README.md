# CryptoInsight 🔍

A Spring Boot REST API that fetches live cryptocurrency trading pair data from [DexScreener](https://dexscreener.com/) and performs risk analysis - helping you identify safe tokens vs potential rug pulls at a glance.

---

## 🚀 Features

- Fetch real-time token data (price, liquidity, volume, market cap) for any DEX trading pair
- Automatic risk scoring: **SAFE**, **MODERATE**, or **HIGH RISK**
- Detailed analysis breakdown: liquidity status, volume status, price trend, and rug pull risk
- Lightweight chart endpoint for powering frontend price/volume displays

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.11 |
| Lombok | Latest |
| Maven | Wrapper included |
| DexScreener API | Public (no key required) |

---

## 📁 Project Structure

```
src/main/java/com/avk/CryptoInsight/
├── CryptoInsightApplication.java     # Entry point
├── Client/
│   └── DexScreenerClient.java        # Calls DexScreener API
├── Config/
│   └── RestTemplateConfig.java       # HTTP client bean
├── Controller/
│   └── CoinInsightController.java    # REST endpoints
├── Dto/
│   ├── DexPairResponse.java          # Maps DexScreener JSON response
│   ├── CoinInsightResponse.java      # Full insight response
│   ├── ChartResponse.java            # Lightweight chart response
│   └── AnalysisResponse.java         # Risk analysis breakdown
└── Service/
    ├── CoinInsightService.java        # Core business logic
    ├── RiskCalculationService.java    # Risk scoring logic
    └── RiskResult.java               # Risk result record
```

---

## ⚙️ Configuration

Add the following to your `src/main/resources/application.properties`:

```properties
dexscreener.base-url=https://api.dexscreener.com/latest
```

---

## 🏃 Running the App

**Prerequisites:** Java 21, Maven (or use the included wrapper)

```bash
# Clone the repository
git clone <your-repo-url>
cd CryptoInsight

# Run with Maven wrapper
./mvnw spring-boot:run

# Or build and run the JAR
./mvnw clean package
java -jar target/CryptoInsight-0.0.1-SNAPSHOT.jar
```

The server starts on `http://localhost:8080` by default.

---

## 📡 API Endpoints

### Get Full Coin Insight
```
GET /api/coins/{chain}/{pairId}/insight
```

Returns complete token data with risk analysis.

**Example:**
```
GET http://localhost:8080/api/coins/base/0x0fb59...../insight
```

**Response:**
```json
{
  "name": "Example Token",
  "symbol": "EXT",
  "imageUrl": "https://cdn.dexscreener.com/cms/images/9060....,
  "price": 0.00452,
  "liquidity": 210000.0,
  "volume24h": 134500.0,
  "marketCap": 980000.0,
  "riskLevel": "SAFE",
  "riskColor": "GREEN",
  "analysis": {
    "liquidityStatus": "Good",
    "volumeStatus": "High",
    "priceTrend": "Bullish",
    "rugPullRisk": "Low"
  }
}
```

---

### Get Chart Data
```
GET /api/coins/{chain}/{pairId}/chart
```

Returns lightweight data for chart rendering.

**Example:**
```
GET /api/coins/solana/0xDEF456.../chart
```

**Response:**
```json
{
  "symbol": "EXT",
  "chain": "solana",
  "pairId": "0xDEF456...",
  "price": 0.00452,
  "priceChange24h": 5.3,
  "volume24h": 134500.0
}
```

---

## 🧮 Risk Scoring Logic

| Condition | Risk Level | Color |
|---|---|---|
| Liquidity < $50k **OR** Volume < $10k **OR** 24h price drop > 40% | HIGH RISK | 🔴 RED |
| Liquidity < $150k | MODERATE | 🟡 YELLOW |
| All conditions pass | SAFE | 🟢 GREEN |

### Analysis Labels

| Field | Values | Threshold |
|---|---|---|
| `liquidityStatus` | Good / Low | $150,000 |
| `volumeStatus` | High / Low | $100,000 |
| `priceTrend` | Bullish / Bearish | 24h change > 0 |
| `rugPullRisk` | Low / High | Liquidity >= $50,000 |

---

## 🔗 Data Source

All market data is sourced from the [DexScreener public API](https://docs.dexscreener.com/) — no API key required.

---

## 📄 License

This project is open source. Feel free to use and modify it.
