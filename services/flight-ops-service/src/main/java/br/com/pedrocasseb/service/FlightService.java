package br.com.pedrocasseb.service;

import br.com.pedrocasseb.enums.FlightStatus;
import br.com.pedrocasseb.payload.request.FlightRequest;
import br.com.pedrocasseb.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
    FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception;

    Page<FlightResponse> getFlightsByAirlineId(Long airlineId,
                                               Long departureAirportId,
                                               Long arrivalAirportId,
                                               Pageable pageable);
    FlightResponse getFlightById(Long id) throws Exception;
    FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception;
    FlightResponse changeStatus(Long id, FlightStatus flightStatus) throws Exception;
    void deleteFlight(Long airlineId,Long id) throws Exception;
}
