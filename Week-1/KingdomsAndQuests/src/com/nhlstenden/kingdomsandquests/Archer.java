package com.nhlstenden.kingdomsandquests;

public class Archer extends Character
{
    private static final int DOUBLE_DAMAGE = 2;

    Archer()
    {
    }

    public void useSpecialAbility()
    {
        int attackPower = getAttackPower();
        setAttackPower(attackPower*DOUBLE_DAMAGE);
    }
}