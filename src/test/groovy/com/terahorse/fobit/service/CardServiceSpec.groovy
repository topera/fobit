package com.terahorse.fobit.service

import com.terahorse.fobit.UnitSpec

class CardServiceSpec extends UnitSpec {

    private cardService = new CardService()

    def "get all cards"() {
        when:
        def cards = cardService.cards

        then:
        cards.size() == 20
    }

}
