package com.terahorse.fobit.model;

import java.util.ArrayList;
import java.util.List;

import static com.terahorse.fobit.Constants.MAX_ROUNDS;

public class Game {

    /**
     * Players that belongs to this game
     */
    private List<Player> players = new ArrayList<>();

    /**
     * The max rounds that this game can have
     * If currentRound reaches maxRound the game is over
     */
    private int maxRounds = MAX_ROUNDS;

    /**
     * The counter of current round (current cycle)
     */
    private int currentRound; // default 0

    /**
     * If the game is still running
     */
    private boolean running = true;

    /**
     * How many points the user did
     */
    private int score; // default 0

    /**
     * List of cards used in this game.
     * Contains cards of all players
     */
    private List<Card> cards = new ArrayList<>();

    /**
     * State of each player in each round
     */
    private List<RoundLog> roundLogs = new ArrayList<>();

    /**
     * Level of user and enemy.
     */
    private Integer level;

    public Game() {
        this.level = 1;
    }

    public Game(Integer level) {
        this.level = level;
    }

    /**
     * The game score is the user score - computer score
     */
    @SuppressWarnings("NumericCastThatLosesPrecision")
    public void calculateScore() {
        Player humanPlayer = findPlayer(true);
        Player computerPlayer = findPlayer(false);
        if (humanPlayer == null || computerPlayer == null) {
            score = 0;
            return;
        }

        humanPlayer.calculateScore();
        computerPlayer.calculateScore();

        double roundsBoost = (1 - (currentRound / maxRounds));
        double humanBoost = humanPlayer.isAlive() ? roundsBoost : 0;
        double computerBoost = computerPlayer.isAlive() ? roundsBoost : 0;

        score = (int) ((humanBoost * humanPlayer.getScore()) - (computerBoost * computerPlayer.getScore()));
    }

    public Player findPlayer(boolean human){
        for (Player player : players) {
            if (human && player.isHuman()) {
                return player;
            }
            if (!human && !player.isHuman()) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<RoundLog> getRoundLogs() {
        return roundLogs;
    }

    public void addRoundLog(RoundLog roundLog) {
        roundLogs.add(roundLog);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
