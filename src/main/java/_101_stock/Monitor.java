package _101_stock;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Monitor {

    private static final double INCREASE_RATE = 5.0;
    private static final double DECREASE_RATE = 5.0;
    static Logger logger = Logger.getLogger(Monitor.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    return "\u001B[31m" + formatMessage(record) + "\u001B[0m\n";
                } else if (record.getLevel().intValue() == Level.INFO.intValue()) {
                    return "\u001B[32m" + formatMessage(record) + "\u001B[0m\n";
                } else {
                    return formatMessage(record) + "\n";
                }
            }
        });
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
    }

    // 持仓成本
    private final double costPrice;
    // 股票代码
    private final String stockCode;
    // 涨幅
    private final double increaseRate;
    // 跌幅
    private final double decreaseRate;

    public Monitor(String stockCode, double costPrice) {
        this.costPrice = costPrice;
        this.stockCode = stockCode;
        this.increaseRate = INCREASE_RATE;
        this.decreaseRate = DECREASE_RATE;
    }

    public Monitor(String stockCode, double costPrice, double rate) {
        this.costPrice = costPrice;
        this.stockCode = stockCode;
        this.increaseRate = rate;
        this.decreaseRate = rate;
    }

    protected Integer getSleepTime() {
        return 60;
    }

    void monitor() throws Exception {
        StockInfo stockInfo = getStockInfo();
        // 股票名称
        String stockName = stockInfo.getName();
        // 当前价
        double currentPrice = stockInfo.getCurrentPrice();
        // 当前价比开盘价涨幅大于等于INCREASE_RATE时，卖出；当前价比开盘价跌幅大于等于DECREASE_RATE时，买入。
        double rate = ((currentPrice - costPrice) / costPrice) * 100;
        // 保留两位小数
        rate = (double) Math.round(rate * 100) / 100;
        if (rate >= increaseRate) {
            String msg = String.format("%s 当前价：%.2f，涨 %.2f%%，卖出", stockName, currentPrice, rate);
            alert(msg);
        } else if (rate <= -decreaseRate) {
            String msg = String.format("%s 当前价：%.2f，跌 %.2f%%，买入", stockName, currentPrice, rate);
            alert(msg);
        } else {
            String msg = String.format("%s 当前价：%.2f，持仓涨跌：%.2f%%，不操作", stockName, currentPrice, rate);
            logger.log(Level.INFO, msg);

        }
    }

    private void alert(String msg) {
        // 打开电脑计算器
        try {
            logger.log(Level.WARNING, msg);
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "打开计算器异常", e);
        }
    }

    protected StockInfo getStockInfo() throws Exception {
        StockService stockService = new StockService();
        return stockService.getStockInfo(stockCode);
    }

    void start() {
        while (true) {
            try {
                monitor();
                TimeUnit.SECONDS.sleep(getSleepTime());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "监控异常", e);
                return;
            }
        }
    }
}
