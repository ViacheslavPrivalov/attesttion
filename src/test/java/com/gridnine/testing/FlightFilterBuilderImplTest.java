package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FlightFilterBuilderImplTest {

    List<Flight> allFlights = new ArrayList<>();
    List<Flight> actualListDepartureBeforeNow = new ArrayList<>();

    List<Flight> actualListArrivalBeforeDeparture = new ArrayList<>();

    List<Flight> actualListMoreTwoHours = new ArrayList<>();

    FlightFilterBuilder filter;


    @BeforeEach
    public void initTestFlights() {
        LocalDateTime inTwoHours = LocalDateTime.now().plusHours(2);


        Flight normalFlight = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.plusHours(4))
        ));


        Flight flightTwoSegments = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.plusHours(2)),
                new Segment(inTwoHours.plusHours(3), inTwoHours.plusHours(7))
        ));


        Flight departureBeforeNow1 = new Flight(List.of(
                new Segment(inTwoHours.minusDays(4), inTwoHours)
        ));


        Flight departureBeforeNow2 = new Flight(List.of(
                new Segment(inTwoHours.minusWeeks(1), inTwoHours)
        ));


        Flight arrivalBeforeDeparture1 = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.minusHours(8))
        ));


        Flight arrivalBeforeDeparture2 = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.minusDays(3))
        ));


        Flight moreTwoHours1 = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.plusHours(2)),
                new Segment(inTwoHours.plusHours(5), inTwoHours.plusHours(6))
        ));


        Flight moreTwoHours2 = new Flight(List.of(
                new Segment(inTwoHours, inTwoHours.plusHours(2)),
                new Segment(inTwoHours.plusHours(3), inTwoHours.plusHours(5)),
                new Segment(inTwoHours.plusHours(6), inTwoHours.plusHours(9))
        ));


        allFlights = Arrays.asList(normalFlight, flightTwoSegments, departureBeforeNow1, departureBeforeNow2, arrivalBeforeDeparture1, arrivalBeforeDeparture2, moreTwoHours1, moreTwoHours2);
        actualListDepartureBeforeNow = Arrays.asList(departureBeforeNow1, departureBeforeNow2);
        actualListArrivalBeforeDeparture = Arrays.asList(arrivalBeforeDeparture1, arrivalBeforeDeparture2);
        actualListMoreTwoHours = Arrays.asList(moreTwoHours1, moreTwoHours2);

        filter = new FlightFilterBuilderImpl(allFlights);

    }


    @Test
    void departureUpToTheCurrentTime() {

        List<Flight> expected = filter.filterDepartureBeforeNow();
        assertEquals(expected, actualListDepartureBeforeNow);
    }

    @Test
    void arrivalDateBeforeDepartureDate() {

        List<Flight> expected = filter.filterArrivalBeforeDeparture();
        assertEquals(expected, actualListArrivalBeforeDeparture);
    }

    @Test
    void flightTotalTimeMoreTwoHours() {

        List<Flight> expected = filter.filterSumTimeOnGroundMoreThanTwoHours();
        assertEquals(expected, actualListMoreTwoHours);
    }
}