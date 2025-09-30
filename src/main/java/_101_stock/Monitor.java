package _101_stock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                // 找到日志中所有百分比数字，如果大于0，就用红色显示，如果小于0，就用绿色显示，否则用默认颜色显示
                String msg = record.getMessage();
                Pattern pattern = Pattern.compile("-?\\d+\\.\\d+%");
                Matcher matcher = pattern.matcher(msg);
                while (matcher.find()) {
                    double rate = Double.parseDouble(matcher.group().replace("%", ""));
                    if (rate > 0) {
                        msg = msg.replace(matcher.group(), "\u001B[31m" + matcher.group() + "\u001B[0m");
                    } else if (rate < 0) {
                        msg = msg.replace(matcher.group(), "\u001B[32m" + matcher.group() + "\u001B[0m");
                    }
                }
                // 找到日志中“持有”，把股票名称用黄色显示
                if (msg.contains("持有")) {
                    msg = msg.replace("《", "\u001B[33m《").replace("》", "》\u001B[0m");
                }
                record.setMessage(msg);
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    return "\u001B[31m" + "警告：" + "\u001B[0m" + formatMessage(record) + "\n";
                } else if (record.getLevel().intValue() == Level.INFO.intValue()) {
                    return "\u001B[32m" + "正常：" + "\u001B[0m" + formatMessage(record) + "\n";
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
    // 是否监控当前正处于最低价
    private final boolean isWatchLowestPrice;
    // 是否监控当前正处于最高价
    private final boolean isWatchHighestPrice;
    // 是否持仓
    private boolean isHold;

    public void setHold(boolean hold) {
        isHold = hold;
    }

    public Monitor(String stockCode, double costPrice, boolean isWatchHighestPrice) {
        this(stockCode, costPrice, INCREASE_RATE, DECREASE_RATE, false);
    }

    public Monitor(String stockCode, double costPrice) {
        this(stockCode, costPrice, INCREASE_RATE, DECREASE_RATE, false);
    }

    public Monitor(String stockCode, double costPrice, double rate) {
        this(stockCode, costPrice, rate, rate, false);
    }

    public Monitor(String stockCode, double costPrice, double increaseRate, double decreaseRate) {
        this(stockCode, costPrice, increaseRate, decreaseRate, false);
    }

    public Monitor(String stockCode, double costPrice, double increaseRate, double decreaseRate, boolean isWatchLowestPrice) {
        this.costPrice = costPrice;
        this.stockCode = stockCode;
        this.increaseRate = increaseRate;
        this.decreaseRate = decreaseRate;
        this.isWatchLowestPrice = isWatchLowestPrice;
        this.isWatchHighestPrice = false;
    }

    public Monitor(String stockCode, double costPrice, double increaseRate, double decreaseRate, boolean isWatchLowestPrice, boolean isWatchHighestPrice) {
        this.costPrice = costPrice;
        this.stockCode = stockCode;
        this.increaseRate = increaseRate;
        this.decreaseRate = decreaseRate;
        this.isWatchLowestPrice = isWatchLowestPrice;
        this.isWatchHighestPrice = isWatchHighestPrice;
    }

    public Monitor(String stockCode, double costPrice, double increaseRate, double decreaseRate, boolean isWatchLowestPrice, boolean isWatchHighestPrice, boolean isHold) {
        this.costPrice = costPrice;
        this.stockCode = stockCode;
        this.increaseRate = increaseRate;
        this.decreaseRate = decreaseRate;
        this.isWatchLowestPrice = isWatchLowestPrice;
        this.isWatchHighestPrice = isWatchHighestPrice;
        this.isHold = isHold;
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
        // 最低价
        double lowPrice = stockInfo.getLow();
        // 最高价
        double highPrice = stockInfo.getHigh();
        // 涨跌幅
        double changePercent = stockInfo.getChangePercent();
        // 当前价比开盘价涨幅大于等于INCREASE_RATE时，卖出；当前价比开盘价跌幅大于等于DECREASE_RATE时，买入。
        double rate = ((currentPrice - costPrice) / costPrice) * 100;
        // 保留两位小数
        rate = (double) Math.round(rate * 100) / 100;
        if (rate >= increaseRate && isHold) {
            String msg = String.format("%s，%tT 《%s》 当前价：%.2f，涨 %.2f%%，卖出", hold(isHold), System.currentTimeMillis(), stockName, currentPrice, rate);
            alert(msg);
        } else if (rate <= -decreaseRate) {
            String msg = String.format("%s，%tT 《%s》 当前价：%.2f，跌 %.2f%%，买入", hold(isHold), System.currentTimeMillis(), stockName, currentPrice, rate);
            alert(msg);
        } else {
            String msg = String.format("%s，%tT 《%s》 当前价：%.2f，最低价：%.2f，最高价：%.2f，当日涨幅：%.2f%%，持仓涨幅：%.2f%%，不操作", hold(isHold), System.currentTimeMillis(), stockName, currentPrice, lowPrice, highPrice, changePercent, rate);
            logger.log(Level.INFO, msg);
        }
        if (isWatchLowestPrice && currentPrice <= lowPrice) {
            String msg = String.format("%tT 《%s》 当前价：%.2f，处于当日最低价：%.2f，买入", System.currentTimeMillis(), stockName, currentPrice, lowPrice);
            alert(msg);
        }
        if (isWatchHighestPrice && currentPrice >= highPrice) {
            String msg = String.format("%tT 《%s》 当前价：%.2f，处于当日最高价：%.2f，卖出", System.currentTimeMillis(), stockName, currentPrice, stockInfo.getHigh());
            alert(msg);
        }
    }

    private String hold(boolean isHold) {
        return isHold ? "持有" : "未持";
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

                // 下午3点后自动退出
                if (LocalDateTime.now().getHour() >= 15 && LocalDateTime.now().getMinute() >= 1) {
                    System.out.println("下午3点后自动退出");
                    break;
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "监控异常", e);
                return;
            }
        }
    }
}
