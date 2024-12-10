package _101_stock;

public class StockServiceTest {
    public static void main(String[] args) {
        StockService stockService = new StockService();
        try {
            StockInfo stockInfo = stockService.getStockInfo("sh600519"); // 例如，贵州茅台的股票代码
            System.out.println("Stock Code: " + stockInfo.getCode());
            System.out.println("Stock Name: " + stockInfo.getName());
            System.out.println("Current Price: " + stockInfo.getCurrentPrice());
            System.out.println("Change Percent: " + stockInfo.getChangePercent() + "%");
            System.out.println(stockInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}