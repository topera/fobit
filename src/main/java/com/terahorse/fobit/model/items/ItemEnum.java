package com.terahorse.fobit.model.items;

import java.util.Arrays;

public enum ItemEnum {

    COIN    (1, "COIN",         "CH",    10,    10),
    FARM    (2, "FARM",         "SF",    30,    20),
    MEAT    (3, "MEAT",         "ML",    500,   250),
    MINE    (4, "MINE",         "SM",    1000,  500),

    SHOES   (18, "SHOES",       "SS",    100,   10),
    BELT    (7, "BELT",         "HB",     10,   5),
    GLOVES  (15, "GLOVES",      "RG",     40,   5),
    PANTS   (17, "PANTS",       "UP",    170,   10),
    SHOULDERS(24, "SHOULDERS",  "GS",    200,   5),
    BRACERS (12, "BRACERS",     "SB",    150,   5),
    HELMET  (16, "HELMET",      "SH",    300,   15),
    HSHIELD (29, "HSHIELD",     "HS",    400,   20),
    ARMOR   (5, "ARMOR",        "LA",    700,   25),
    CLOAK   (13, "CLOAK",       "EC",    100,   3),
    PSHIELD (27, "PSHIELD",     "PS",    900,   25),

    AXE     (9, "AXE",          "HA",    100,   1),
    SWORD   (10, "SWORD",       "GS",    300,   2),
    ROCKS   (26, "ROCKS",       "IR",    400,   3),
    THUNDER (28, "THUNDER",     "HT",    600,   4),
    FIRE    (25, "FIRE",        "HF",    1000,  5);

    public final Integer CARD_NUMBER;
    public final String CODE;
    public final String SHORT_CODE;
    public final Integer COST;
    public final Integer BENEFIT;

    ItemEnum(Integer cardNumber, String code, String shortCode, Integer cost, Integer benefit) {
        this.CARD_NUMBER = cardNumber;
        this.CODE = code;
        this.SHORT_CODE = shortCode;
        this.COST = cost;
        this.BENEFIT = benefit;
    }

    public static ItemEnum findItem(Integer cardNumber){
        return Arrays.stream(values()).filter(i -> i.CARD_NUMBER.equals(cardNumber)).findFirst().orElse(null);
    }

}
