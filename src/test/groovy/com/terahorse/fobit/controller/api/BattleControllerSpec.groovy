package com.terahorse.fobit.controller.api

import com.terahorse.fobit.IntegrationSpec
import com.terahorse.fobit.model.Game
import org.junit.Test

class BattleControllerSpec extends IntegrationSpec {

    @Test
    def "battle with no cards"() {
        when:
        def game = readPage("/api/battle/1/0", Game)

        then:
        game.players.size() == 2
    }

}
