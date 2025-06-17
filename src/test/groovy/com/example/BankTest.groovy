package com.example

import spock.lang.Specification

class BankTest extends Specification {

    def "reduce money same currency"() {
        expect:
        new Bank().reduce(Money.dollar(1), "USD") == Money.dollar(1)
    }

    def "reduce money different currency"() {
        given:
        def bank = new Bank()
        bank.addRate("CHF", "USD", 2)

        expect:
        bank.reduce(Money.franc(2), "USD") == Money.dollar(1)
    }

    def "identity rate"() {
        expect:
        new Bank().rate("USD", "USD") == 1
    }
}
