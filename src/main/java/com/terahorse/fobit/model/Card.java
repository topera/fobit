package com.terahorse.fobit.model;

public class Card {

    /**
     * Identifies card
     * Ranges from 1 to 32
     */
    private int id;
    private String group;
    private String name;
    private String shortName;
    private String desc;
    private String goodDesc;
    private String badDesc;
    private Integer level;

    /**
     * Player owner of this card
     */
    private Player player;

    public Card() {
    }

    public Card(int id, Player player) {
        this.id = id;
        this.player = player;
    }

    public Card(int id, String name, String shortName, String goodDesc, String badDesc) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.goodDesc = goodDesc;
        this.badDesc = badDesc;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public String getBadDesc() {
        return badDesc;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
