package com.terahorse.fobit.service;

import com.terahorse.fobit.exceptions.FobitLoopDetectedException;
import com.terahorse.fobit.model.Card;
import com.terahorse.fobit.model.Game;
import com.terahorse.fobit.model.Player;
import com.terahorse.fobit.model.RoundLog;
import com.terahorse.fobit.util.CardUtil;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.terahorse.fobit.Constants.MAX_FIRED_RULES;
import static com.terahorse.fobit.Constants.MAX_KIE_SESSION_EXECUTIONS;

@Component
public class GameService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(GameService.class);
    private static Map<Integer, String> computerCards = new HashMap<>();

    private CardUtil cardUtil = new CardUtil();

    static {
        computerCards.put(1, "00000101");
        computerCards.put(2, "00000000");
        computerCards.put(3, "00000000");
        computerCards.put(4, "00000000");
        computerCards.put(5, "00000000");
        computerCards.put(6, "00000000");
        computerCards.put(7, "00000000");
        computerCards.put(8, "00000000");
        computerCards.put(9, "00000000");
        computerCards.put(10, "00000000");
    }

    public Player createHumanPlayer(String cardCode) {
        Player player = new Player("😃 HumanPlayer", false);
        player.setHuman(true);
        player.setCardsCode(cardCode);
        return player;
    }

    public Player createComputerPlayer(int level) {
        Player player = new Player("💻 ComputerPlayer", true);
        player.setHuman(false);
        player.setCardsCode(getComputerCode(level));
        return player;
    }

    public String getComputerCode(Integer level) {
        return computerCards.get(level);
    }

    public void runGame(Game game){
        addCardsInGame(game);
        int roundExecutions = 0;
        while (game.isRunning()) {
            debugRound(game);
            runRound(game);
            roundExecutions++;

            if (roundExecutions == MAX_KIE_SESSION_EXECUTIONS) {
                throw new FobitLoopDetectedException("Max kieSession reached! Stopping the game!");
            }
        }
        game.calculateScore();
        debugRound(game);
    }

    private void runRound(Game game) {
        KieSession kieSession = createKieSession();
        kieSession.insert(game);
        insertAllPlayers(game, kieSession);
        insertAllCards(game.getCards(), kieSession);
        kieSession.fireAllRules(MAX_FIRED_RULES);
        finishRound(game.getPlayers());
    }

    private void debugRound(Game game) {
        Player humanPlayer = game.findPlayer(true);
        Player computerPlayer = game.findPlayer(false);

        if (humanPlayer == null || computerPlayer == null) {
            return;
        }

        debugPlayer(humanPlayer);
        debugPlayer(computerPlayer);

        game.addRoundLog(new RoundLog(humanPlayer.copy(), computerPlayer.copy()));
    }

    private void debugPlayer(Player player) {
        LOG.info("┃ ▬ {}: alive:{}, life:{}/{}, money:{}, armor:{}, attacks:{}/{}, strength:{}, attack:{}, score:{}",
                player.getName(),
                player.isAlive(),
                player.getCurrentLife(),
                player.getMaxLife(),
                player.getMoney(),
                player.getArmor(),
                player.getRemainingAttacks(),
                player.getSpeed(),
                player.getStrength(),
                player.isAttacking(),
                player.getScore()
        );
    }

    private void finishRound(List<Player> players) {
        for (Player player: players){
            player.setAttacking(!player.isAttacking());
        }
    }

    private void insertAllCards(List<Card> cards, KieSession kieSession) {
        for (Card card : cards) {
            kieSession.insert(card);
        }
    }

    private void insertAllPlayers(Game game, KieSession kieSession) {
        for (Player player: game.getPlayers()){
            kieSession.insert(player);
        }
    }

    public void runGame(Player player, Card card){
        KieSession kieSession = createKieSession();
        kieSession.insert(new Game());
        kieSession.insert(player);
        if (card != null) {
            kieSession.insert(card);
        }
        kieSession.fireAllRules();
    }

    private void addCardsInGame(Game game) {
        for (Player player: game.getPlayers()) {
            List<Card> cards = cardUtil.createPlayerCards(player);
            game.addCards(cards);
        }
        debugCards(game);
    }

    private void debugCards(Game game) {
        for (Card card : game.getCards()) {
            LOG.info("Found card for player {}, with id {} ", card.getPlayer().getName(), card.getId());
        }
    }

}
