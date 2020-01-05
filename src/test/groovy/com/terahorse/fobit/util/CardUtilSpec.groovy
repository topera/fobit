package com.terahorse.fobit.util

import com.terahorse.fobit.UnitSpec
import com.terahorse.fobit.model.Player

class CardUtilSpec extends UnitSpec {

    def cardUtil = new CardUtil()

    def "convertCardCodeToFlags"() {
        expect:
        cardUtil.convertCardCodeToFlags(cardCode) == flags

        where:
        cardCode                | flags
        '0'                     | "00000000000000000000000000000000" // user selected no cards
        '1'                     | "00000000000000000000000000000001" // user selected card 1
        '2'                     | "00000000000000000000000000000010" // user selected card 2
        'A'                     | "00000000000000000000000000001010" // user selected cards 4 and 1
        'F'                     | "00000000000000000000000000001111" // user selected first 4 cards
        '10'                    | "00000000000000000000000000010000"
        'FF'                    | "00000000000000000000000011111111"
        'ABC'                   | "00000000000000000000101010111100"
        'FFFF'                  | "00000000000000001111111111111111"
        'FFFFFFF'               | "00001111111111111111111111111111"
        'FFFFFFFF'              | "11111111111111111111111111111111" // user selected all 32 cards
    }

    def "convertCardCodeToCards: count"() {
        expect:
        cardUtil.convertCardCodeToCards(cardCode).size() == cardSize

        where:
        cardCode    | cardSize
        '0'         | 0
        '1'         | 1
        '2'         | 1
        'A'         | 2
        'F'         | 4
        '10'        | 1
        'FF'        | 8
        'ABC'       | 7
        'FFFF'      | 16
        'FFFFFFF'   | 28
        'FFFFFFFF'  | 32
    }

    def "convertCardCodeToCards: check ids"() {
        expect:
        cardUtil.convertCardCodeToCards(cardCode)*.id == ids

        where:
        cardCode        | ids
        '0'             | []
        '1'             | [1]
        '2'             | [2]
        'A'             | [2, 4]
        'F'             | [1, 2, 3, 4]
        '10'            | [5]
        'FF'            | [1, 2, 3, 4, 5, 6, 7, 8]
        'ABC'           | [3, 4, 5, 6, 8, 10, 12]
        'FFFF'          | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
        'FFFFFFFF'      | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32]
    }

    def "create players card"() {
        expect:
        cardUtil.createPlayerCards(new Player()) == []
    }


}
