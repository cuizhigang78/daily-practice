package _101_stock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// 交易记录类
@Getter
@Setter
@RequiredArgsConstructor
public class TransactionRecord {
    private final LocalDate date;
    private final String name;
    private final String operation;
    private final double price;
}