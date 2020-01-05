package com.terahorse.fobit;

public final class Constants {

    /**
     * If must display agenda details in log
     */
    public static final boolean DEBUG_RULES = true;

    /**
     * Computer cards code: which will be the strategy of the enemy of the user
     * In the future we will use Algorithm Genetic to know which is the hardest code ever!
     */
    public static final String COMPUTER_CARDS_CODE = "0";

    /**
     * Weight to calculate score in currentLife
     */
    public static final int W_CURRENT_LIFE = 1;

    /**
     * How many rounds/kieSessions we can execute
     * Useful to stop eternal loops
     */
    public static final int MAX_KIE_SESSION_EXECUTIONS = 9999;

    /**
     * Max rules fired in a kie session.
     * Useful to stop eternal loops
     */
    public static final int MAX_FIRED_RULES = 9999;

    /**
     * Max round a game can have
     */
    public static final int MAX_ROUNDS = 100;

    /**
     * Max allowed speed for a player.
     * Ex: 10 means 10 attacks per round
     */
    public static final int MAX_SPEED = 10;

    /**
     * Start life of all players
     */
    public static final int INITIAL_MAX_LIFE = 100;

    /**
     * Max life a player can have
     */
    public static final int FINAL_MAX_LIFE = 999;

    /**
     * Start armor of all players
     */
    public static final float INITIAL_ARMOR = 0F;

    /**
     * Start strength of all players
     */
    public static final int INITIAL_STRENGTH = 10;

    /**
     * Start strength of all players
     */
    public static final int INITIAL_SPEED = 1;

    private Constants(){
    }

}
