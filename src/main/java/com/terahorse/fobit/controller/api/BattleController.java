package com.terahorse.fobit.controller.api;

import com.terahorse.fobit.model.Game;
import com.terahorse.fobit.model.Player;
import com.terahorse.fobit.service.GameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BattleController {

    private static final Logger LOG = LoggerFactory.getLogger(BattleController.class);

    @Autowired
    private GameService gameService;

    @RequestMapping("/api/battle/{level}/{cardCode}")
    @Cacheable("battle")
    public Game runBattle(@PathVariable Integer level, @PathVariable String cardCode) {
        LOG.info("Starting battle with cardCode {}", cardCode);

        Player humanPlayer = gameService.createHumanPlayer(cardCode);
        Player computerPlayer = gameService.createComputerPlayer(level);

        Game game = new Game(level);
        game.addPlayer(humanPlayer);
        game.addPlayer(computerPlayer);

        gameService.runGame(game);
        return game;
    }

}
