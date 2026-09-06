package com.nhlstenden.kingdomsandquests;

public class Mage extends Character
{
    private final static int DEFENCE_INCREASE = 50;

    Mage()
    {
    }

    public void useSpecialAbility()
    {
        int defence = getDefence();
        setDefence(defence + DEFENCE_INCREASE);
    }
}
