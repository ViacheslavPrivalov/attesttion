package com.gridnine.testing;


import com.gridnine.testing.entity.Flight;

import java.util.List;


public interface FlightFilterBuilder {


    List<Flight> filterDepartureBeforeNow();

    List<Flight> filterArrivalBeforeDeparture();

    List<Flight> filterSumTimeOnGroundMoreThanTwoHours();
}
