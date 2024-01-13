package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.FlightBuilder;

import java.util.List;


public class Main {
    public static void showFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterBuilder filter = new FlightFilterBuilderImpl(flights);


        System.out.println("Unfiltered flies:");
        showFlights(flights);

        System.out.println("Departure before now:");
        showFlights(filter.filterDepartureBeforeNow());

        System.out.println("Arrival before departure:");
        showFlights(filter.filterArrivalBeforeDeparture());

        System.out.println("Time on the ground is more than two hours:");
        showFlights(filter.filterSumTimeOnGroundMoreThanTwoHours());
    }
}
