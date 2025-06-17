package com.example

import spock.lang.Specification

class MoneyTest extends Specification {

    def "multiplication"() {
        expect:
        Money.dollar(5).times(2) == Money.dollar(10)
        Money.franc (5).times(3) == Money.franc (15)
    }

    def "equality within same currency"() {
        expect:
        Money.dollar(5) == Money.dollar(5)
        Money.franc (5) != Money.franc (6)
    }

    def "currency"() {
        expect:
        Money.dollar(1).currency() == "USD"
        Money.franc (1).currency() == "CHF"
    }

    def "simple addition"() {
        when:
        def result = new Bank().reduce(
                Money.dollar(5).plus(Money.dollar(5)), "USD"
        )
        then:
        result == Money.dollar(10)
    }

    def "mixed addition"() {
        given:
        Expression fiveBucks  = Money.dollar(5)
        Expression tenFrancs = Money.franc(10)
        def bank = new Bank()
        bank.addRate("CHF", "USD", 2)

        when:
        def result = bank.reduce(fiveBucks.plus(tenFrancs), "USD")

        then:
        result == Money.dollar(10)
    }

    def "plus returns Sum"() {
        given:
        def five = Money.dollar(5)
        when:
        def sum = five.plus(five)
        then:
        sum instanceof Sum
        ((Sum) sum).augend == five
        ((Sum) sum).addend == five
    }
}
