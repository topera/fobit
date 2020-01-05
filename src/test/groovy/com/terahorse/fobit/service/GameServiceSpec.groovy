package com.terahorse.fobit.service


import com.terahorse.fobit.UnitSpec
import com.terahorse.fobit.model.Game
import com.terahorse.fobit.model.Player

class GameServiceSpec extends UnitSpec {

    private gameService = new GameService()

    def "Run empty game"() {
        when:
        def game = new Game()
        gameService.runGame(game)

        then:
        game.players == []
    }

    def "Run game with 3 players - checking attack inversion"() {
        when:
        def game = new Game()
        game.addPlayer(new Player("p1", true))
        game.addPlayer(new Player("p2", false))
        game.addPlayer(new Player("p3", false))
        gameService.runGame(game)

        then:
        game.players.size() == 3
        game.players[0].attacking
        !game.players[1].attacking
        !game.players[2].attacking
    }

    def "Run game with 2 players"() {
        when:
        def game = new Game()
        game.addPlayer(new Player("p1", false))
        game.addPlayer(new Player("p2", true))
        gameService.runGame(game)

        then:
        game.players.size() == 2
        game.players[0].maxLife == 100
        game.players[1].maxLife == 100
    }

    def "Create computer and human player"() {
        when:
        def humanPlayer = gameService.createHumanPlayer("1")
        def computerPlayer = gameService.createComputerPlayer(1)

        then:
        humanPlayer.human
        humanPlayer.cardsCode == "1"
        !computerPlayer.human
        computerPlayer.cardsCode == gameService.getComputerCode(1)
    }

}
