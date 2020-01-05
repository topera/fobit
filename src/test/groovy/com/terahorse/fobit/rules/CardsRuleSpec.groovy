package com.terahorse.fobit.rules

import com.terahorse.fobit.Constants
import com.terahorse.fobit.model.Card
import com.terahorse.fobit.model.Game
import com.terahorse.fobit.model.Player
import com.terahorse.fobit.model.items.Item
import com.terahorse.fobit.model.items.ItemEnum

import static com.terahorse.fobit.Constants.MAX_SPEED

class CardsRuleSpec extends BaseRuleSpec {

    def INITIAL_STR = 2
    def INITIAL_ARMOR = Constants.INITIAL_ARMOR
    def INITIAL_LIFE = 100

    def "currentRound: test to get max value"() {
        when:
        def game = new Game()
        runRules(game)

        then:
        !game.running
        game.currentRound == Constants.MAX_ROUNDS
    }

    def "currentRound: after max value"() {
        when:
        def game = new Game()
        game.currentRound = Constants.MAX_ROUNDS + 1
        runRules(game)

        then:
        !game.running
        game.currentRound == Constants.MAX_ROUNDS
    }

    def "maxLife: min value"() {
        when:
        def player = new Player()
        player.maxLife = 23
        runRules(player)

        then:
        player.maxLife == Constants.INITIAL_MAX_LIFE
    }

    def "maxLife: max value"() {
        when:
        def player = new Player()
        player.maxLife = 50000
        runRules(player)

        then:
        player.maxLife == Constants.FINAL_MAX_LIFE
    }

    def "speed: test with value 1"() {
        when:
        def player = new Player()
        player.speed = 1
        runRules(player)

        then:
        player.speed == 1
    }

    def "speed: test with max value"() {
        when:
        def player = new Player()
        player.speed = MAX_SPEED
        runRules(player)

        then:
        player.speed == MAX_SPEED
    }

    def "speed: test with max value + 10"() {
        when:
        def player = new Player()
        player.speed = MAX_SPEED + 10
        runRules(player)

        then:
        player.speed == MAX_SPEED
    }

    def "card #01"() {
        when:
        def player = new Player()
        runRules3Times(player, new Card(ItemEnum.COIN.CARD_NUMBER, player))

        then:
        checkPlayer(player, INITIAL_ARMOR, ItemEnum.COIN.BENEFIT * 2, 2, [new Item(ItemEnum.COIN)], INITIAL_LIFE - ItemEnum.COIN.COST)
    }

    def "card #02"() {
        expect:
        testResourceCard(ItemEnum.FARM)
    }

    def "card #03"() {
        expect:
        testResourceCard(ItemEnum.MEAT)
    }

    def "card #04"() {
        expect:
        testResourceCard(ItemEnum.MINE)
    }

    def "card #05"() {
        expect:
        testArmorCard(ItemEnum.ARMOR)
    }

    def "card #07"() {
        expect:
        testArmorCard(ItemEnum.BELT)
    }

    def "card #09"() {
        expect:
        testWeaponCard(ItemEnum.AXE)
    }

    def "card #10"() {
        expect:
        testWeaponCard(ItemEnum.SWORD)
    }

    def "card #12"() {
        expect:
        testArmorCard(ItemEnum.BRACERS)
    }

    def "card #13"() {
        expect:
        testArmorCard(ItemEnum.CLOAK)
    }

    def "card #15"() {
        expect:
        testArmorCard(ItemEnum.GLOVES)
    }

    def "card #16"() {
        expect:
        testArmorCard(ItemEnum.HELMET)
    }

    def "card #17"() {
        expect:
        testArmorCard(ItemEnum.PANTS)
    }

    def "card #18"() {
        expect:
        testArmorCard(ItemEnum.SHOES)
    }

    def "card #24"() {
        expect:
        testArmorCard(ItemEnum.SHOULDERS)
    }

    def "card #25"() {
        expect:
        testWeaponCard(ItemEnum.FIRE)
    }

    def "card #26"() {
        expect:
        testWeaponCard(ItemEnum.ROCKS)
    }

    def "card #27"() {
        expect:
        testArmorCard(ItemEnum.PSHIELD)
    }

    def "card #28"() {
        expect:
        testWeaponCard(ItemEnum.THUNDER)
    }

    def "card #29"() {
        expect:
        testArmorCard(ItemEnum.HSHIELD)
    }

    private Boolean testResourceCard(ItemEnum itemEnum){
        def player = createPoorPlayer(itemEnum)
        runRules(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithoutResource(player, itemEnum)
        player = createRichPlayer(itemEnum)
        runRules3Times(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithResource(player, itemEnum)
        return true
    }

    private Boolean testArmorCard(ItemEnum itemEnum){
        def player = createPoorPlayer(itemEnum)
        runRules(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithoutArmor(player, itemEnum)
        player = createRichPlayer(itemEnum)
        runRules3Times(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithArmor(player, itemEnum)
        return true
    }

    private Boolean testWeaponCard(ItemEnum itemEnum){
        def player = createPoorPlayer(itemEnum)
        runRules(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithoutWeapon(player, itemEnum)
        player = createRichPlayer(itemEnum)
        runRules3Times(player, new Card(itemEnum.CARD_NUMBER, player))
        assert userWithWeapon(player, itemEnum)
        return true
    }

    private Player createPoorPlayer(ItemEnum itemEnum) {
        def player = new Player()
        player.money = invalidAmount(itemEnum)
        return player
    }

    private Player createRichPlayer(ItemEnum itemEnum) {
        def player = new Player()
        player.money = validAmount(itemEnum)
        return player
    }

    int invalidAmount(ItemEnum itemEnum) {
        return itemEnum.COST - 1
    }

    int validAmount(ItemEnum itemEnum) {
        return itemEnum.COST * 3
    }

    boolean userWithoutResource(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, INITIAL_ARMOR, invalidAmount(itemEnum), INITIAL_STR, [])
    }

    boolean userWithResource(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, INITIAL_ARMOR, validAmount(itemEnum) - itemEnum.COST + itemEnum.BENEFIT * 2, INITIAL_STR, [new Item(itemEnum)])
    }

    boolean userWithoutWeapon(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, INITIAL_ARMOR, invalidAmount(itemEnum), INITIAL_STR, [])
    }

    boolean userWithWeapon(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, INITIAL_ARMOR, validAmount(itemEnum) - itemEnum.COST, INITIAL_STR + itemEnum.BENEFIT, [new Item(itemEnum)])
    }

    boolean userWithoutArmor(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, INITIAL_ARMOR, invalidAmount(itemEnum), INITIAL_STR, [])
    }

    boolean userWithArmor(Player player, ItemEnum itemEnum) {
        return checkPlayer(player, (INITIAL_ARMOR + itemEnum.BENEFIT/100) as float, validAmount(itemEnum) - itemEnum.COST, INITIAL_STR, [new Item(itemEnum)])
    }

    def checkPlayer(Player player, Float armor, Integer money, Integer strength, List<Item> items, int life=INITIAL_LIFE) {
        assert player.currentLife == life
        assert player.armor == armor
        assert player.money == money
        assert player.strength == strength
        assert player.items == items
        return true
    }

    void runRules3Times(Player player, Card card) {
        3.times {
            runRules(player, card)
        }
    }

}
