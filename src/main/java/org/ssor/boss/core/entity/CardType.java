package org.ssor.boss.core.entity;

/**
 * @author Derrian Harris
 */
public enum CardType {
    CARD_PLAIN("Plain"),
    CARD_GOLD("Gold"),
    CARD_PLATINUM("Platinum");

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    public int index() {
        return ordinal();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
