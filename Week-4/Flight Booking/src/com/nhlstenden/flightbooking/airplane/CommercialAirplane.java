package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;

public class CommercialAirplane extends Airplane
{
    private int economySeats;
    private int economySeatsTaken;
    private int businessSeats;
    private int businessSeatsTaken;

    public CommercialAirplane(String code, double currentFuelLevelInLiters, int totalNumOfSeats, int economySeats, int businessSeats)
    {
        super(code, currentFuelLevelInLiters, totalNumOfSeats);
        this.setEconomySeats(economySeats);
        this.setEconomySeatsTaken(0);
        this.setBusinessSeats(businessSeats);
        this.setBusinessSeatsTaken(0);
    }

    public int getEconomySeats()
    {
        return this.economySeats;
    }

    public void setEconomySeats(int economySeats)
    {
        if (economySeats == (getTotalNumOfSeats() - getBusinessSeats()))
        {
            throw new IllegalArgumentException("Invalid set of economy seats");
        }

        this.economySeats = economySeats;
    }

    public int getBusinessSeats()
    {
        return this.businessSeats;
    }

    public void setBusinessSeats(int businessSeats)
    {
        if (businessSeats != getTotalNumOfSeats() - getEconomySeats())
        {
            throw new IllegalArgumentException("Invalid set of business seats");
        }

        this.businessSeats = businessSeats;
    }

    public int getEconomySeatsTaken()
    {
        return this.economySeatsTaken;
    }

    public void setEconomySeatsTaken(int economySeatsTaken)
    {
        if (economySeatsTaken < 0 || economySeatsTaken > getEconomySeats())
        {
            throw new IllegalArgumentException("Taken economy seats can not be negative or more than the total amount of economy seats");
        }

        this.economySeatsTaken = economySeatsTaken;
    }

    public int getBusinessSeatsTaken()
    {
        return this.businessSeatsTaken;
    }

    public void setBusinessSeatsTaken(int businessSeatsTaken)
    {
        if (businessSeatsTaken < 0 || businessSeatsTaken > getBusinessSeats())
        {
            throw new IllegalArgumentException("Taken business seats can not be negative or more than the total amount of business seats");
        }

        this.businessSeatsTaken = businessSeatsTaken;
    }

    @Override
    public double getFuelConsumptionInLiters(Flight flight)
    {
        return getEconomySeats()*1.75 + getBusinessSeats()*1.98*flight.getDistanceInKm() + getEconomySeatsTaken()*2.02 + getBusinessSeatsTaken()*2.87 + flight.getAirplane().getTotalLuggageWeightInKg()*0.3;
    }
}
