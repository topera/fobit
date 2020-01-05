package com.terahorse.fobit.util;

import com.terahorse.fobit.model.Card;
import com.terahorse.fobit.model.Player;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardUtil {

    private static final String HEXADECIMAL_PREFIX = "0x";
    private static final char CHAR_EMPTY = ' ';
    private static final char CHAR_ZERO = '0';
    private static final String CHAR_ONE = "1";
    private static final int LAST_POSITION = 31;


    public List<Card> createPlayerCards(Player player){
        return convertCardCodeToCards(player.getCardsCode(), player);
    }

    public List<Card> convertCardCodeToCards(String cardCode){
        return convertCardCodeToCards(cardCode, null);
    }

    private List<Card> convertCardCodeToCards(String cardCode, Player player){
        String flags = convertCardCodeToFlags(cardCode);
        List<Card> cards = new ArrayList<>();

        for(int position = LAST_POSITION; position >= 0; position--){
            String flag = flags.substring(position, position + 1);
            if (flag.equals(CHAR_ONE)) {
                // user selected the card in this position
                // lets add it to the cards list
                Card card = new Card((LAST_POSITION + 1) - position, player);
                cards.add(card);
            }
        }
        return cards;
    }

    public String convertCardCodeToFlags(String cardCode){
        // we use long instead of int because integers cannot store 32 combinations
        // This happens because int uses one bit for the signal (negative numbers).
        String resultWithoutPadding = Long.toBinaryString(Long.decode(HEXADECIMAL_PREFIX + cardCode));
        return String.format("%32s", resultWithoutPadding).replace(CHAR_EMPTY, CHAR_ZERO);
    }

    private void readCardsFromCSV(String csvFile) {

    }

}
