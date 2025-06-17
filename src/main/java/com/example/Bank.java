package com.example;

import java.util.HashMap;
import java.util.Map;
public class Bank {
    private final Map<Pair<String, String>, Integer> rates = new HashMap<>();

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair<>(from, to), rate);
    }

    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
    }

    public Money convert(Money source, String toCurrency) {
        int rate = rate(source.currency(), toCurrency);
        return new Money(source.amount() / rate, toCurrency);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        Integer r = rates.get(new Pair<>(from, to));
        if (r == null) {
            throw new IllegalArgumentException(
                    "No exchange rate from " + from + " to " + to);
        }
        return r;
    }
}
