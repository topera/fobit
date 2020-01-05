package com.terahorse.fobit.model

import com.terahorse.fobit.UnitSpec

class GameSpec extends UnitSpec {

    def "Test of getters/setters"() {
        when:
        def game = new Game()
        game.currentRound = 1
        game.maxRounds = 2
        game.running = true
        game.score = 10
        game.addRoundLog(new RoundLog(null, null))
        game.addRoundLog(new RoundLog(null, null))

        then:
        game.currentRound == 1
        game.maxRounds == 2
        game.running
        game.score == 10
        game.roundLogs.size() == 2
    }

    def "Adding/removing cards"() {
        when:
        def game = new Game()
        game.addCards([new Card(1, new Player("john", false))])

        then:
        game.cards[0].id == 1
        game.cards[0].player.name == "john"
    }

    def "not finding player in empty game"() {
        when:
        def game = new Game()

        then:
        game.findPlayer(true) == null
        game.findPlayer(false) == null
        game.players == []
    }

    def "finding human player"() {
        when:
        def game = new Game()
        game.addPlayer(new Player("john", true))

        then:
        game.findPlayer(true).name == "john"
        game.findPlayer(false) == null
    }

    def "finding robot player"() {
        when:
        def game = new Game()
        def player = new Player("john", true)
        player.human = false
        game.addPlayer(player)

        then:
        game.findPlayer(false).name == "john"
        game.findPlayer(true) == null
    }

    def "calculate game score of empty game"() {
        when:
        def game = new Game()
        game.calculateScore()

        then:
        game.score == 0
    }

    def "calculate game score"() {
        given:
        def game = new Game()
        def humanPlayer = new Player("john", true)
        def computerPlayer = new Player("robot", true)
        computerPlayer.human = false
        game.addPlayer(humanPlayer)
        game.addPlayer(computerPlayer)

        when:
        game.calculateScore()

        then:
        game.score == 0
    }

}
