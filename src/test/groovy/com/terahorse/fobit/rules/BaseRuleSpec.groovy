package com.terahorse.fobit.rules

import com.terahorse.fobit.UnitSpec
import com.terahorse.fobit.model.Card
import com.terahorse.fobit.model.Game
import com.terahorse.fobit.model.Player
import com.terahorse.fobit.service.GameService

abstract class BaseRuleSpec extends UnitSpec {

    def playerService = new GameService()

    protected Player runRules(Player player, Card card=null) {
        return playerService.runGame(player, card)
    }

    protected Game runRules(Game game) {
        return playerService.runGame(game)
    }

}
