package _101_stock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockInfo {
    private String code;
    private String name;
    /**
     * 今开
     */
    private double open;
    /**
     * 最高
     */
    private double high;
    /**
     * 最低
     */
    private double low;
    /**
     * 成交量
     */
    private long volume;
    /**
     * 成交额
     */
    private double amount;
    /**
     * 换手率
     */
    private double turnoverRate;
    /**
     * 昨收
     */
    private double preClose;

    private double currentPrice;
    private double changePercent;

    // Getters and setters
}