package com.nhlstenden.kingdomsandquests;

public class Warrior extends Character
{
    private static final int ATTACK_POWER_INCREASE = 100;

    Warrior()
    {
    }

    public void useSpecialAbility()
    {
        int attackPower = getAttackPower();
        setAttackPower(attackPower + ATTACK_POWER_INCREASE);
    }
}

