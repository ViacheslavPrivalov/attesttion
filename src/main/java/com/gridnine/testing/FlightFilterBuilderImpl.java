package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterBuilderImpl implements FlightFilterBuilder {

    private List<Flight> flights;

    public FlightFilterBuilderImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }


    @Override
    public List<Flight> filterDepartureBeforeNow() {
        List<Flight> listBeforeNowDate = flights.stream().
                filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().
                                isBefore(LocalDateTime.now()))).collect(Collectors.toList());

        return listBeforeNowDate;
    }

    @Override
    public List<Flight> filterArrivalBeforeDeparture() {
        List<Flight> listArrivalBeforeDeparture = flights.stream().
                filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().
                                isBefore(segment.getDepartureDate()))).collect(Collectors.toList());

        return listArrivalBeforeDeparture;
    }

    @Override
    public List<Flight> filterSumTimeOnGroundMoreThanTwoHours() {
        List<Flight> listMoreTwoHours = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            long totalHours = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime depTime = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrTime = segments.get(i).getArrivalDate();

                long hoursDifference = Math.abs(depTime.getHour() - arrTime.getHour());
                totalHours += hoursDifference;

                if (totalHours >= 2) {
                    listMoreTwoHours.add(flight);
                }
            }
        }
        return listMoreTwoHours;
    }
}
