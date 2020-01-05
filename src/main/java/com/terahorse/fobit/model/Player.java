package com.terahorse.fobit.model;

import com.terahorse.fobit.model.items.Item;
import com.terahorse.fobit.model.items.ItemEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.terahorse.fobit.Constants.INITIAL_ARMOR;
import static com.terahorse.fobit.Constants.INITIAL_MAX_LIFE;
import static com.terahorse.fobit.Constants.INITIAL_SPEED;
import static com.terahorse.fobit.Constants.INITIAL_STRENGTH;
import static com.terahorse.fobit.Constants.W_CURRENT_LIFE;

@SuppressWarnings("UnusedReturnValue")
public class Player {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    private String name;

    /**
     * If the player is attacking we will only attack in that round.
     * Otherwise will just defend.
     */
    private boolean attacking; // default false

    /**
     * The max of life that can have
     * Range: BEGIN_MAX_LIFE to END_MAX_LIFE
     *
     * Examples:
     * 100: begin of the game
     */
    private int maxLife = INITIAL_MAX_LIFE;

    /**
     * The amount of current life
     * Range: 0 to maxLife
     * When reaches 0, the player is dead.
     *
     * Examples:
     * 0: dead
     * 1: almost dead
     */
    private int currentLife = maxLife;

    /**
     * The damaged caused by this player when it attacks
     * Range: 0 to 100
     *
     * Examples:
     * 0: causes no damage.
     * 80: causes a lot of damage!
     */
    private int strength = INITIAL_STRENGTH;

    /**
     * The protection to attacks
     * Range: 0% to 100%
     *
     * Examples:
     * 0%: player receives all damages
     * 50: player receives half of damages
     * 100%: player receives no damage at all!
     */
    private float armor = INITIAL_ARMOR;

    /**
     * How fast this player attacks. This is the number of attacks in each turn.
     * Range: 1 to MAX_SPEED
     * Examples:
     * 1: player hits the enemy one time per turn
     * 4: player hits the enemy four times per turn!
     */
    private int speed = INITIAL_SPEED;

    /**
     * How many attacks this player still has in this round
     * Range: 0 to speed
     */
    private int remainingAttacks; // default 0

    /**
     * If this player is alive
     * Examples:
     * true: player alive and will continue on the battle
     * false: player dead.
     */
    private boolean alive = true;

    /**
     * CardsCode of this user.
     * A CardsCode is a hexadecimal string that when converted to binary,
     * will tell us which cards the user has chosen.
     *
     * It ranges from 0 to 2^³²
     *
     * For example (hexa/binary comparision):
     * hex 0                    - bin 00000000000000000000000000000000 (user selected 0 cards)
     * hex 4                    - bin 00000000000000000000000000000100 (user selected 3rd card)
     * hex F                    - bin 00000000000000000000000000001111 (user selected cards 1,2,3 and 4)
     * Integer.MAX_VALUE    - bin 11111111111111111111111111111111 (user selected all cards)
     */
    private String cardsCode = "0";

    /**
     * If this player is human.
     * If false, is the computer.
     */
    private boolean human = true;

    /**
     * Player score
     */
    private int score; // default 0

    /**
     * Player money
     */
    private int money = 0; // default 0

    /**
     * List of items that user have
     */
    private List<Item> items = new ArrayList<>();

    public Player() {
    }

    public Player(String name, boolean attacking) {
        this.name = name;
        this.attacking = attacking;
    }

    void calculateScore() {
        score = W_CURRENT_LIFE * currentLife;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isAttacking() {
        return attacking;
    }

    @SuppressWarnings("unused")
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRemainingAttacks() {
        return remainingAttacks;
    }

    public void setRemainingAttacks(int remainingAttacks) {
        this.remainingAttacks = remainingAttacks;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getCardsCode() {
        return cardsCode;
    }

    public void setCardsCode(String cardsCode) {
        this.cardsCode = cardsCode;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @SuppressWarnings("unused")
    public void buyWeapon(ItemEnum itemEnum){
        if (money < itemEnum.COST) {
            return;
        }
        LOG.info("Player " + this.getName() + " is buying weapon " + itemEnum.SHORT_CODE + " (cost: " + itemEnum.COST + ")");
        money -= itemEnum.COST;
        strength += itemEnum.BENEFIT;
        addItem(new Item(itemEnum));
    }

    @SuppressWarnings("unused")
    public void buyArmor(ItemEnum itemEnum){
        if (money < itemEnum.COST) {
            return;
        }
        LOG.info("Player " + this.getName() + " is buying armor " + itemEnum.SHORT_CODE + " (cost: " + itemEnum.COST + ")");
        money -= itemEnum.COST;
        armor += itemEnum.BENEFIT/100f;
        addItem(new Item(itemEnum));
    }

    @SuppressWarnings("unused")
    public void buyResource(ItemEnum itemEnum, Boolean payWithLife){
        if (payWithLife) {
            if (currentLife < itemEnum.COST) {
                return;
            }
        } else {
            if (money < itemEnum.COST) {
                return;
            }
        }

        LOG.info("Player " + this.getName() + " is buying resource " + itemEnum.SHORT_CODE + " (cost: " + itemEnum.COST + "). Pay with life: " + payWithLife);
        if (payWithLife) {
            currentLife -= itemEnum.COST;
        } else {
            money -= itemEnum.COST;
        }
        addItem(new Item(itemEnum));
    }

    @SuppressWarnings("unused")
    public void buyResource(ItemEnum itemEnum){
        buyResource(itemEnum, false);
    }

    @SuppressWarnings("unused")
    public void receiveMoney(ItemEnum itemEnum){
        money += itemEnum.BENEFIT;
    }

    @SuppressWarnings("unused")
    public boolean missingItem(ItemEnum itemEnum){
        return !hasItem(itemEnum);
    }

    @SuppressWarnings("unused")
    public boolean hasItem(ItemEnum itemEnum){
        return items.contains(new Item(itemEnum));
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Player copy() {
        Player clone = new Player(name, attacking);
        clone.setMaxLife(maxLife);
        clone.setCurrentLife(currentLife);
        clone.setStrength(strength);
        clone.setArmor(armor);
        clone.setSpeed(speed);
        clone.setRemainingAttacks(remainingAttacks);
        clone.setAlive(alive);
        clone.setCardsCode(cardsCode);
        clone.setHuman(human);
        clone.setScore(score);
        clone.setMoney(money);
        clone.setItems(new ArrayList<>(items));
        return clone;
    }

}
