package com.nhlstenden.kingdomsandquests;

public abstract class Character
{
    private static final int BASE_ATTACK_POWER = 89;
    private static final int BASE_DEFENCE = 673;
    private int attackPower;
    private int defence;
    private boolean defeated;

    public Character()
    {
        this.setAttackPower(BASE_ATTACK_POWER);
        this.setDefence(BASE_DEFENCE);
        this.setDefeated(false);
    }


    public int getAttackPower()
    {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower)
    {
        if (attackPower < BASE_ATTACK_POWER)
        {
            throw new IllegalArgumentException("Attack Power can not be less than the base attack power");
        }

        this.attackPower = attackPower;
    }

    public int getDefence()
    {
        return this.defence;
    }

    public void setDefence(int defence)
    {
        if (defence < BASE_DEFENCE)
        {
            throw new IllegalArgumentException("Defence can not be less than base defence");
        }

        this.defence = defence;
    }

    public boolean isDefeated()
    {
        return this.defeated;
    }

    public void setDefeated(boolean defeated)
    {
        this.defeated = defeated;
    }

    public void attack(Character characterBeingAttacked)
    {
        characterBeingAttacked.setDefence(characterBeingAttacked.getDefence() - getAttackPower());
    }

    public void resetStats()
    {
        setAttackPower(BASE_ATTACK_POWER);
    }

    public abstract void useSpecialAbility();
}