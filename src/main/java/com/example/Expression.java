package com.example;

/** Любое «денежное выражение» умеет:
 *  – сворачиваться (reduce) к одной валюте через Bank;
 *  – складываться (plus) с другим выражением;
 *  – умножаться (times) на число. */
public interface Expression {
    Money reduce(Bank bank, String toCurrency);
    Expression plus(Expression addend);
    Expression times(int multiplier);
}
