package com.nhlstenden.kingdomsandquests;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private static final int NEEDED_POINTS_TO_LEVEL_UP = 200;
    private List<Quest> quests;
    private List<Character> characters;
    private int xp;
    private int level;
    private boolean inQuest;
    private List<Item> items;

    public Player()
    {
        this.setQuests(new ArrayList<>());
        this.setCharacters(new ArrayList<>());
        this.setXp(0);
        this.setLevel(1);
        this.setInQuest(false);
        this.setItems(new ArrayList<>());
    }

    public List<Quest> getQuests()
    {
        return new ArrayList<>(this.quests);
    }

    public void setQuests(List<Quest> quests)
    {
        if (quests == null)
        {
            throw new IllegalArgumentException("quests cannot be null");
        }

        for (Quest quest : quests)
        {
            if (quest == null)
            {
                throw new IllegalArgumentException("A quest from quests cannot be null");
            }
        }

        this.quests = new ArrayList<>(quests);
    }

    public List<Character> getCharacters()
    {
        return new ArrayList<>(this.characters);
    }

    public void setCharacters(List<Character> characters)
    {
        if (characters == null)
        {
            throw new IllegalArgumentException("characters cannot be null");
        }

        for (Character character : characters)
        {
            if (character == null)
            {
                throw new IllegalArgumentException("A character from characters cannot be null");
            }
        }

        this.characters = new ArrayList<>(characters);
    }

    public int getXp()
    {
        return this.xp;
    }

    public void setXp(int xp)
    {
        if (xp < 0)
        {
            throw new IllegalArgumentException("The xp can not be below 0");
        }

        this.xp = xp;
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        if (level < 0)
        {
            throw new IllegalArgumentException("Level can not be below 0");
        }

        this.level = level;
    }

    public boolean isInQuest()
    {
        return this.inQuest;
    }

    public void setInQuest(boolean inQuest)
    {
        this.inQuest = inQuest;
    }

    public List<Item> getItems()
    {
        return new ArrayList<>(this.items);
    }

    public void setItems(List<Item> items)
    {
        if (items == null)
        {
            throw new IllegalArgumentException("items cannot be null");
        }

        for (Item item : items)
        {
            if (item == null)
            {
                throw new IllegalArgumentException("An item from items cannot be null");
            }
        }

        this.items = new ArrayList<>(items);
    }

    public void levelUp()
    {
        int currentLevel = getLevel();
        int currentXp = getXp();
        int correctLevel = currentXp/NEEDED_POINTS_TO_LEVEL_UP + 1;

        if (correctLevel != currentLevel)
        {
            setLevel(correctLevel);
        }
        else
        {
            int neededXpPoints = currentLevel * NEEDED_POINTS_TO_LEVEL_UP - currentXp;
            throw new IllegalArgumentException("You need " + neededXpPoints + " more points to level up!");
        }
    }

    public void startQuest(Character character, Quest quest)
    {
        if (isInQuest())
        {
            throw new IllegalArgumentException("The player is already in a quest. Wait for the end of the quest!");
        }
        if (getXp() < quest.getNeededXp())
        {
            throw new IllegalArgumentException("You don't have enough XP for this quest!");
        }
        if (character == null)
        {
            throw new IllegalArgumentException("The character can not be null");
        }
        if (quest == null)
        {
            throw new IllegalArgumentException("The quest can not be null");
        }
        boolean characterFound = false;
        for (Character character1 : getCharacters())
        {
            if (character.equals(character1))
            {
                characterFound = true;
                break;
            }
        }
        if (!characterFound)
        {
            throw new IllegalArgumentException("The character has not been found!");
        }
        boolean questFound = false;
        for (Quest quest1 : getQuests())
        {
            if (quest.equals(quest1))
            {
                questFound = true;
                break;
            }
        }
        if (!questFound)
        {
            throw new IllegalArgumentException("The quest has not been found!");
        }
        setInQuest(true);

        fightCharacter(character, quest);
        endQuest(quest);
    }

    public void fightCharacter(Character character, Quest quest)
    {
        Character player = character; // more readable name
        Character questCharacter = quest.getCharacterToPlayAgainst(); // more readable name

        int attackPowerPlayer = player.getAttackPower();
        int defensePlayer = player.getDefence();

        int attackPowerQuestCharacter = questCharacter.getAttackPower();
        int defenseQuestCharacter = questCharacter.getDefence();

        player.useSpecialAbility(); // Special ability applied to player
        questCharacter.useSpecialAbility(); // Special ability applied to Quest Character

        player.attack(questCharacter); // One attack by the player
        questCharacter.attack(player); // One attack by the Quest Character

        player.resetStats(); // Resetting stats of the player
        questCharacter.resetStats(); // Resetting stats of the Quest Character

        int attacksNeededPlayerKillQuestCharacter = defenseQuestCharacter/attackPowerPlayer; // calculate attacks needed by the player to kill the Quest Character
        int attacksNeededQuestCharacterKillPlayer = defensePlayer/attackPowerQuestCharacter; // calculate attacks needed by the Quest Character to kill the player

        if (attacksNeededPlayerKillQuestCharacter > attacksNeededQuestCharacterKillPlayer)
        {
            questCharacter.setDefeated(true);
        }
    }

    public boolean isCharacterDefeated(Quest quest)
    {
        if (quest == null)
        {
            throw new IllegalArgumentException("You can not have a null quest");
        }

        for (Quest quest1 : getQuests())
        {
            if (quest.equals(quest1))
            {
                return quest.getCharacterToPlayAgainst().isDefeated();
            }
        }

        throw new IllegalArgumentException("No such quest was found in the list of quests");
    }

    public void endQuest(Quest quest)
    {
        if (isCharacterDefeated(quest))
        {
            quest.setCompleted(true);
            setXp(getXp() + quest.getOfferedXp());

            if (quest instanceof SpecialQuest specialQuest)
            {
                List<Item> itemsFromQuest = specialQuest.getItems();
                List<Item> currentItems = getItems();
                currentItems.addAll(itemsFromQuest);
                setItems(currentItems);
            }
            setInQuest(false);
        }
    }
}