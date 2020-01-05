package com.terahorse.fobit.rules

import com.terahorse.fobit.Constants
import com.terahorse.fobit.model.Game
import com.terahorse.fobit.model.Player

import static com.terahorse.fobit.Constants.MAX_SPEED

class SetupRuleSpec extends BaseRuleSpec {

    def "currentRound: test to get max value"() {
        when:
        def game = new Game()
        runRules(game)

        then:
        !game.running
        game.currentRound == Constants.MAX_ROUNDS
    }

    def "currentRound: after max value"() {
        when:
        def game = new Game()
        game.currentRound = Constants.MAX_ROUNDS + 1
        runRules(game)

        then:
        !game.running
        game.currentRound == Constants.MAX_ROUNDS
    }

    def "maxLife: min value"() {
        when:
        def player = new Player()
        player.maxLife = 23
        runRules(player)

        then:
        player.maxLife == Constants.INITIAL_MAX_LIFE
    }

    def "maxLife: max value"() {
        when:
        def player = new Player()
        player.maxLife = 50000
        runRules(player)

        then:
        player.maxLife == Constants.FINAL_MAX_LIFE
    }

    def "speed: test with value 1"() {
        when:
        def player = new Player()
        player.speed = 1
        runRules(player)

        then:
        player.speed == 1
    }

    def "speed: test with max value"() {
        when:
        def player = new Player()
        player.speed = MAX_SPEED
        runRules(player)

        then:
        player.speed == MAX_SPEED
    }

    def "speed: test with max value + 10"() {
        when:
        def player = new Player()
        player.speed = MAX_SPEED + 10
        runRules(player)

        then:
        player.speed == MAX_SPEED
    }

    def "remainingAttacks: reload test with default values"() {
        when:
        def player = new Player("red", true)
        runRules(player)

        then:
        player.speed == 5
        player.remainingAttacks == 5
    }

    def "remainingAttacks: reload test with NO default values"() {
        when:
        def player = new Player("red", true)
        player.speed = 5
        runRules(player)

        then:
        player.speed == 5
        player.remainingAttacks == 5
    }

    def "remainingAttacks: reload test with a NO attacking player"() {
        when:
        def player = new Player("red", false)
        player.speed = 3
        runRules(player)

        then:
        player.remainingAttacks == 0
    }

}
