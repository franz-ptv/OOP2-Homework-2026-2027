package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.CommercialAirplane;
import com.nhlstenden.flightbooking.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightTest
{
    private Airport a1;
    private Airport a2;
    private CommercialAirplane c1;
    private Flight flight;

    @BeforeEach
    void setup()
    {
        Airport a1 = new Airport("LAX");
        Airport a2 = new Airport("AMS");
        CommercialAirplane c1 = new CommercialAirplane("123", 10, 100, 50, 50);
        Flight flight = new Flight(a1, a2, c1, LocalDateTime.now());
        flight.setDistanceInKm(8956);
    }

    @Test
    void hasSufficientFuel_properAirplaneValues_expectNotToThrow()
    {
        assertTrue(flight.hasSufficientFuel(c1));
    }


}
