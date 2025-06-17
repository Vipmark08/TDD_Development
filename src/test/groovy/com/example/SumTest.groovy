package com.example

import spock.lang.Specification

class SumTest extends Specification {

    def "sum plus money"() {
        given:
        Expression fiveBucks  = Money.dollar(5)
        Expression tenFrancs = Money.franc(10)
        def bank = new Bank()
        bank.addRate("CHF", "USD", 2)

        when:
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks)
        def result = bank.reduce(sum, "USD")

        then:
        result == Money.dollar(15)
    }

    def "sum times money"() {
        given:
        Expression fiveBucks  = Money.dollar(5)
        Expression tenFrancs = Money.franc(10)
        def bank = new Bank()
        bank.addRate("CHF", "USD", 2)

        when:
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2)
        def result = bank.reduce(sum, "USD")

        then:
        result == Money.dollar(20)
    }
}
