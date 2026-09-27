package com.nhlstenden.flightbooking.luggage;

public class Luggage
{
    private double weightInKg;
    private LuggageType luggageType;

    public Luggage(double weightInKg, LuggageType luggageType)
    {
        this.setWeightInKg(weightInKg);
        this.setLuggageType(luggageType);
    }

    public double getWeightInKg()
    {
        return this.weightInKg;
    }

    public void setWeightInKg(double weightInKg)
    {
        if (weightInKg <= 0)
        {
            throw new IllegalArgumentException("Weight can not be a negative or 0");
        }

        this.weightInKg = weightInKg;
    }

    public LuggageType getLuggageType()
    {
        return this.luggageType;
    }

    public void setLuggageType(LuggageType luggageType)
    {
        if (luggageType != null)
        {
            throw new IllegalArgumentException("Luggage type can not be null");
        }

        this.luggageType = luggageType;
    }
}
