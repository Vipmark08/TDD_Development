package com.example;

import java.util.Objects;

/** Value Object «деньги»: сумма + валюта, без побочных эффектов. */
public class Money implements Expression {
    private int    amount;
    private final String currency;

    public Money(int amount, String currency) {
        this.amount   = amount;
        this.currency = currency;
    }

    public static Money dollar(int a) { return new Money(a, "USD"); }
    public static Money franc (int a) { return new Money(a, "CHF"); }

    public int    amount()   { return amount;   }
    public String currency() { return currency; }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        if (currency.equals(toCurrency)) {
            return this;
        }
        return bank.convert(this, toCurrency);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) return false;
        Money m = (Money) obj;
        return amount == m.amount && currency.equals(m.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
