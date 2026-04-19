package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.enums.FlightStatus;
import br.com.pedrocasseb.mapper.FlightMapper;
import br.com.pedrocasseb.model.Flight;
import br.com.pedrocasseb.payload.request.FlightRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import br.com.pedrocasseb.payload.response.AirportResponse;
import br.com.pedrocasseb.payload.response.FlightResponse;
import br.com.pedrocasseb.repository.FlightRespository;
import br.com.pedrocasseb.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRespository flightRespository;

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception {
        if(flightRespository.existsByFlightNumber(flightRequest.getFlightNumber())) {
            throw new Exception("flight already exists");
        }
        Flight flight = FlightMapper.toEntity(flightRequest);
        flight.setAirlineId(airlineId);
        Flight savedFlight = flightRespository.save(flight);
        return convertToFlightResponse(savedFlight);
    }

    @Override
    public Page<FlightResponse> getFlightsByAirlineId(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable) {
        return flightRespository.findByAirlineId(airlineId, departureAirportId, arrivalAirportId, pageable)
                .map(this::convertToFlightResponse);
    }

    @Override
    public FlightResponse getFlightById(Long id) throws Exception {
        Flight flight = flightRespository.findById(id).orElseThrow(
                ()-> new Exception("flight not found")
        );
        return convertToFlightResponse(flight);
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception {
        Flight existing = flightRespository.findById(id).orElseThrow(
                ()-> new Exception("flight not found")
        );

        if(flightRequest.getFlightNumber() != null &&
                flightRespository.existsByFlightNumberAndIdNot(flightRequest.getFlightNumber(), id)) {
            throw new Exception("flight already exists");
        }
        FlightMapper.updateEntity(flightRequest, existing);
        Flight updatedFlight = flightRespository.save(existing);
        return convertToFlightResponse(updatedFlight);
    }

    @Override
    public FlightResponse changeStatus(Long id, FlightStatus flightStatus) throws Exception {
        Flight flight = flightRespository.findById(id).orElseThrow(
                ()-> new Exception("flight not found")
        );
        flight.setStatus(flightStatus);
        Flight updatedFlight = flightRespository.save(flight);
        return convertToFlightResponse(updatedFlight);
    }

    @Override
    public void deleteFlight(Long airlineId, Long id) throws Exception {
        Flight flight = flightRespository.findByAirlineIdAndId(airlineId, id).orElseThrow(
                ()-> new Exception("flight not found")
        );
        flightRespository.delete(flight);
    }

    public FlightResponse convertToFlightResponse(Flight flight) {
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(flight.getAircraftId())
                .build();
        AirlineResponse airline = AirlineResponse.builder()
                .id(flight.getAirlineId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();

        return FlightMapper.toResponse(
                flight,
                aircraft,
                airline,
                departureAirport,
                arrivalAirport
        );
    }
}
