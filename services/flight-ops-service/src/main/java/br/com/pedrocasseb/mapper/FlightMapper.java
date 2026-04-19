package br.com.pedrocasseb.mapper;

import br.com.pedrocasseb.model.Flight;
import br.com.pedrocasseb.payload.request.FlightRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import br.com.pedrocasseb.payload.response.AirportResponse;
import br.com.pedrocasseb.payload.response.FlightResponse;

public class FlightMapper {
    public static Flight toEntity(FlightRequest request) {
        if (request == null) return null;

        return Flight.builder()
                .flightNumber(request.getFlightNumber())
                .aircraftId(request.getAircraftId())
                .departureAirportId(request.getDepartureAirportId())
                .arrivalAirportId(request.getArrivalAirportId())
                .build();
    }

    public static FlightResponse toResponse(Flight flight,
                                            AircraftResponse aircraftResponse,
                                            AirlineResponse airlineResponse,
                                            AirportResponse departureAirport,
                                            AirportResponse arrivalAirport) {
        if(flight == null) return null;
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airlineResponse)
                .aircraft(aircraftResponse)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FlightRequest request, Flight flight) {
        if (request == null || flight == null) return;
        if(request.getFlightNumber() != null) flight.setFlightNumber(request.getFlightNumber());
        if(request.getAircraftId() != null) flight.setArrivalAirportId(request.getAircraftId());
        if(request.getDepartureAirportId() != null) flight.setDepartureAirportId(request.getDepartureAirportId());
        if(request.getArrivalAirportId() != null) flight.setArrivalAirportId(request.getArrivalAirportId());
        if(request.getStatus() != null) flight.setStatus(request.getStatus());
    }

}
