package com.terahorse.fobit.model

import com.terahorse.fobit.UnitSpec

class CardSpec extends UnitSpec {

    def "Test of getters/setters"() {
        when:
        def card = new Card()

        then:
        card.id == 0
    }

    def "Test of getters/setters 2"() {
        when:
        def card = new Card(1, "2", "3", "4", "5")

        then:
        card.id == 1
        card.name == "2"
        card.shortName == "3"
        card.goodDesc == "4"
        card.badDesc == "5"
    }

    def "Test of constructor"() {
        when:
        def card = new Card(1, new Player("john", true))

        then:
        card.id == 1
        card.player.name == "john"
    }

}
