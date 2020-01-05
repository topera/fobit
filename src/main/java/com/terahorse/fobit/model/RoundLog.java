package com.terahorse.fobit.model;

public class RoundLog {

    /**
     * Stores the state of the human or computer in a specified round
     */
    private Player humanPlayer;
    private Player computerPlayer;

    @SuppressWarnings("unused")
    public RoundLog() {
    }

    public RoundLog(Player humanPlayer, Player computerPlayer) {
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getComputerPlayer() {
        return computerPlayer;
    }

}
