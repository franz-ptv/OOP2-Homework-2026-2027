package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.*;
import com.nhlstenden.flightbooking.luggage.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane
{
    private String code;
    private double currentFuelLevelInLiters;
    private int totalNumOfSeats;
    private int totalSeatsTaken;
    private List<Luggage> luggageList;

    public Airplane(String code, double currentFuelLevelInLiters, int totalNumOfSeats)
    {
        this.setCode(code);
        this.setCurrentFuelLevelInLiters(currentFuelLevelInLiters);
        this.setTotalNumOfSeats(totalNumOfSeats);
        this.setTotalSeatsTaken(0);
        this.setLuggageList(new ArrayList<>());
    }


    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        if (code == null || code.isBlank())
        {
            throw new IllegalArgumentException("code cannot be null or blank");
        }

        this.code = code;
    }

    public double getCurrentFuelLevelInLiters()
    {
        return this.currentFuelLevelInLiters;
    }

    public void setCurrentFuelLevelInLiters(double currentFuelLevelInLiters)
    {
        if (currentFuelLevelInLiters < 0)
        {
            throw new IllegalArgumentException("The current fuel level can not be negative");
        }

        this.currentFuelLevelInLiters = currentFuelLevelInLiters;
    }

    public int getTotalNumOfSeats()
    {
        return this.totalNumOfSeats;
    }

    public void setTotalNumOfSeats(int totalNumOfSeats)
    {
        if (totalNumOfSeats < 0)
        {
            throw new IllegalArgumentException("Total seats can not be negative");
        }

        this.totalNumOfSeats = totalNumOfSeats;
    }

    public int getTotalSeatsTaken()
    {
        return this.totalSeatsTaken;
    }

    public void setTotalSeatsTaken(int totalSeatsTaken)
    {
        if (totalSeatsTaken < 0 || totalSeatsTaken > getTotalNumOfSeats())
        {
            throw new IllegalArgumentException("Total taken seats can not be negative or more than the total amount of seats");
        }

        this.totalSeatsTaken = totalSeatsTaken;
    }

    public int getTotalEmptySeats()
    {
        return getTotalNumOfSeats() - getTotalSeatsTaken();
    }

    public List<Luggage> getLuggageList()
    {
        return new ArrayList<>(this.luggageList);
    }

    public void setLuggageList(List<Luggage> luggageList)
    {
        if (luggageList == null)
        {
            throw new IllegalArgumentException("luggageList cannot be null");
        }

        for (Luggage luggage : luggageList)
        {
            if (luggage == null)
            {
                throw new IllegalArgumentException("A luggage from luggageList cannot be null");
            }
        }

        this.luggageList = new ArrayList<>(luggageList);
    }

    public double getTotalLuggageWeightInKg()
    {
        double total = 0;
        for (Luggage luggage : luggageList)
        {
            total += luggage.getWeightInKg();
        }

        return total;
    }

    public abstract double getFuelConsumptionInLiters(Flight flight);
}
