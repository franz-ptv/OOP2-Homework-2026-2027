package com.nhlstenden.kingdomsandquests;

import java.util.ArrayList;
import java.util.List;

public class SpecialQuest extends Quest
{
    private List<Item> items;

    public SpecialQuest(int offeredXp, int difficultyLevel, List<Item> items)
    {
        super(offeredXp, difficultyLevel);
        this.setItems(items);
    }

    public List<Item> getItems()
    {
        return new ArrayList<>(this.items);
    }

    public void setItems(List<Item> items)
    {
        if (items == null || items.isEmpty())
        {
            throw new IllegalArgumentException("items cannot be null or empty");
        }

        for (Item Item : items)
        {
            if (Item == null)
            {
                throw new IllegalArgumentException("An item from items cannot be null");
            }
        }

        this.items = new ArrayList<>(items);
    }
}