package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;

public class PrivateAirplane extends Airplane
{
    public PrivateAirplane(String code, double currentFuelLevelInLiters, int totalNumOfSeats)
    {
        super(code, currentFuelLevelInLiters, totalNumOfSeats);
    }

    @Override
    public double getFuelConsumptionInLiters(Flight flight)
    {
        return getTotalNumOfSeats()*1.31*flight.getDistanceInKm() + getTotalSeatsTaken()*1.87 + getTotalLuggageWeightInKg()*0.4;
    }
}
