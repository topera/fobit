package com.terahorse.fobit.model

import com.terahorse.fobit.UnitSpec
import com.terahorse.fobit.model.items.Item
import com.terahorse.fobit.model.items.ItemEnum

class PlayerSpec extends UnitSpec {

    def "Test of getters/setters"() {
        when:
        def player = new Player("testPlayer", true)
        player.armor = 0.25F
        player.currentLife = 2
        player.maxLife = 3
        player.speed = 4
        player.strength = 5
        player.remainingAttacks = 6
        player.cardsCode = "7"
        player.score = 8
        player.alive = false
        player.human = true
        player.attacking = false
        player.money = 100
        player.addItem(new Item(ItemEnum.AXE))

        then:
        player.name == "testPlayer"
        player.armor == 0.25F
        player.currentLife == 2
        player.maxLife == 3
        player.speed == 4
        player.strength == 5
        player.remainingAttacks == 6
        player.cardsCode == "7"
        player.score == 8
        !player.alive
        !player.attacking
        player.human
        player.money == 100
        player.items[0] == new Item(ItemEnum.AXE)
    }

    def "copy player"() {
        given:
        def player = new Player("testPlayer", true)
        player.armor = 0.25F
        player.currentLife = 2
        player.maxLife = 3
        player.speed = 4
        player.strength = 5
        player.remainingAttacks = 6
        player.cardsCode = "7"
        player.score = 8
        player.alive = false
        player.human = true
        player.money = 123
        player.addItem(new Item(ItemEnum.AXE))

        when: "the player is cloned"
        def clone = player.copy()

        then:
        clone.name == "testPlayer"
        clone.armor == 0.25F
        clone.currentLife == 2
        clone.maxLife == 3
        clone.speed == 4
        clone.strength == 5
        clone.remainingAttacks == 6
        clone.cardsCode == "7"
        clone.score == 8
        !clone.alive
        clone.attacking
        clone.human
        clone.money == 123
        clone.items[0] == new Item(ItemEnum.AXE)
    }

    def "calculate score"() {
        when:
        def player = new Player("testPlayer", true)
        player.currentLife = 150
        player.calculateScore()

        then:
        player.score == 150
    }

}
