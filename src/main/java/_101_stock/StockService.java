package _101_stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockService {

    public StockInfo getStockInfo(String stockCode) throws Exception {
        String url = "http://hq.sinajs.cn/list=" + stockCode;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("referer","http://finance.sina.com.cn/");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return parseStockInfo(content.toString(), stockCode);
    }

    private StockInfo parseStockInfo(String data, String stockCode) {
        String[] stockData = data.split(",");
        StockInfo stockInfo = new StockInfo();
        stockInfo.setCode(stockCode);
        stockInfo.setName(stockData[0].split("\"")[1]);
        stockInfo.setOpen(Double.parseDouble(stockData[1]));
        stockInfo.setHigh(Double.parseDouble(stockData[4]));
        stockInfo.setLow(Double.parseDouble(stockData[5]));
        stockInfo.setVolume(Integer.parseInt(stockData[8]));
        stockInfo.setAmount(Double.parseDouble(stockData[9]));
        // stockInfo.setTurnoverRate(Double.parseDouble(stockData[38]));
        stockInfo.setPreClose(Double.parseDouble(stockData[2]));
        stockInfo.setCurrentPrice(Double.parseDouble(stockData[3]));
        double previousClose = Double.parseDouble(stockData[2]);
        stockInfo.setChangePercent((stockInfo.getCurrentPrice() - previousClose) / previousClose * 100);
        return stockInfo;
    }


}