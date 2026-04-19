package br.com.pedrocasseb.service;

import br.com.pedrocasseb.payload.request.FlightInstanceRequest;
import br.com.pedrocasseb.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightInstanceService {
    FlightInstanceResponse createFlightInstance(Long userId,FlightInstanceRequest flightInstanceRequest);
    FlightInstanceResponse getFlightInstanceById(Long id);
    Page<FlightInstanceResponse> getByAirlineId(Long airlineId,
                                                Long departureAirportId,
                                                Long arrivalAirportId,
                                                Long flightId,
                                                Long onDate,
                                                Pageable pageable);
    FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request);
    void deleteFlightInstance(Long id);
}
