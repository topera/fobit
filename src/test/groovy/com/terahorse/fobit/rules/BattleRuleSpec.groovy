package com.terahorse.fobit.rules

import com.terahorse.fobit.Constants
import com.terahorse.fobit.model.Game
import com.terahorse.fobit.model.Player

class BattleRuleSpec extends BaseRuleSpec {

    private static final String RED_PLAYER = "😈 RedPlayer"
    private static final String BLUE_PLAYER = "😐 BluePlayer"

    def "currentLife: min value"() {
        when:
        def player = new Player()
        player.currentLife = -1
        runRules(player)

        then:
        player.currentLife == 0
        !player.alive
    }

    def "currentLife: max value"() {
        when:
        def player = new Player()
        player.currentLife = 10000
        runRules(player)

        then:
        player.currentLife == Constants.INITIAL_MAX_LIFE
        player.alive
    }

    def "core: simple attack"() {
        when: "we have a simple battle of two players"
        def red = new Player(RED_PLAYER, true)
        def blue = new Player(BLUE_PLAYER, false)
        runGameWithPlayers(red, blue)

        then: "the red is correct"
        red.alive == true
        red.armor == 0.2F
        red.attacking == false
        red.currentLife == 10
        red.maxLife == Constants.INITIAL_MAX_LIFE
        red.name == RED_PLAYER
        red.remainingAttacks == 0
        red.speed == 5
        red.strength == 2

        and: "the blue is correct"
        blue.alive == false
        blue.armor == 0.2F
        blue.attacking == true
        blue.currentLife == 0
        blue.maxLife == Constants.INITIAL_MAX_LIFE
        blue.name == BLUE_PLAYER
        blue.remainingAttacks == 0
        blue.speed == 5
        blue.strength == 2
    }

    def "core: a strong player defeats another in few rounds"() {
        when:
        def red = new Player(RED_PLAYER, true)
        red.strength = 20
        def blue = new Player(BLUE_PLAYER, false)
        def game = runGameWithPlayers(red, blue)

        then: "the game finishes quickly"
        game.currentRound == 3

        and: "the red is alive"
        red.alive == true
        red.currentLife == 90
        red.remainingAttacks == 3
        red.strength == 20

        and: "the blue is dead"
        blue.alive == false
        blue.currentLife == 0
        blue.remainingAttacks == 0
    }

    def "core: a very strong player defeats another in 1 round"() {
        when:
        def red = new Player(RED_PLAYER, true)
        red.strength = 150
        def blue = new Player(BLUE_PLAYER, false)
        def game = runGameWithPlayers(red, blue)

        then: "the game finishes quickly"
        game.currentRound == 1
    }

    private Game runGameWithPlayers(Player player1, Player player2) {
        def game = new Game()
        game.addPlayer(player1)
        game.addPlayer(player2)
        runRules(game)
        return game
    }

}
