package com.example;

/** Выражение «сумма»: augend + addend. */
public class Sum implements Expression {
    public final Expression augend;
    public final Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        Money m1 = augend.reduce(bank, toCurrency);
        Money m2 = addend.reduce(bank, toCurrency);
        return new Money(m1.amount() + m2.amount(), toCurrency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }
}
