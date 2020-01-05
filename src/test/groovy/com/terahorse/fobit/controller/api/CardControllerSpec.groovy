package com.terahorse.fobit.controller.api

import com.terahorse.fobit.IntegrationSpec
import org.junit.Test

class CardControllerSpec extends IntegrationSpec {

    @Test
    def "cards check"() {
        when: "read cards"
        def cards = readPage("/api/cards", List)

        then:
        cards.size() == 20
    }

}
