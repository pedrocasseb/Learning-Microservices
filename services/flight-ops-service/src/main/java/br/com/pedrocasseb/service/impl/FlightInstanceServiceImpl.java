package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.mapper.FlightInstanceMapper;
import br.com.pedrocasseb.model.Flight;
import br.com.pedrocasseb.model.FlightInstance;
import br.com.pedrocasseb.payload.request.FlightInstanceRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import br.com.pedrocasseb.payload.response.AirportResponse;
import br.com.pedrocasseb.payload.response.FlightInstanceResponse;
import br.com.pedrocasseb.repository.FlightInstanceRepository;
import br.com.pedrocasseb.repository.FlightRespository;
import br.com.pedrocasseb.service.FlightInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightInstanceServiceImpl implements FlightInstanceService {
    private final FlightInstanceRepository flightInstanceRepository;
    private final FlightRespository flightRespository;

    @Override
    public FlightInstanceResponse createFlightInstance(Long airlineId, FlightInstanceRequest request) throws Exception {
        // todo : watch airlineId

        Flight flight = flightRespository.findById(request.getFlightId()).orElseThrow(
                () -> new Exception("Flight not found")
        );

        // todo : service to service communication
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();

        FlightInstance flightInstance = FlightInstanceMapper.toEntity(request, flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        FlightInstance saved = flightInstanceRepository.save(flightInstance);

        // todo : create seat instance

        return convertToFlightInstanceResponse(saved);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {
        FlightInstance flightInstance = flightInstanceRepository.findById(id).orElseThrow(
                () -> new Exception("Flight not found")
        );
        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long airlineId, Long departureAirportId, Long arrivalAirportId, Long flightId, LocalDate onDate, Pageable pageable) {
        LocalDateTime start = onDate != null ? onDate.atStartOfDay() : null;
        LocalDateTime end = onDate != null ? onDate.plusDays(1).atStartOfDay() : null;

        // todo : watch airlineId

        return flightInstanceRepository.findByAirlineId(
                airlineId, departureAirportId, arrivalAirportId, flightId, start, end, pageable
        ).map(this::convertToFlightInstanceResponse);
    }

    @Override
    public FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception {
        FlightInstance existing = flightInstanceRepository.findById(id).orElseThrow(
                () -> new Exception("Flight not found")
        );

        FlightInstanceMapper.updateEntity(request, existing);
        return convertToFlightInstanceResponse(flightInstanceRepository.save(existing));
    }

    @Override
    public void deleteFlightInstance(Long id) throws Exception {
        FlightInstance existing = flightInstanceRepository.findById(id).orElseThrow(
                () -> new Exception("Flight not found")
        );
        flightInstanceRepository.delete(existing);
    }

    private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance) {
        AirlineResponse airline = AirlineResponse.builder().id(flightInstance.getAirlineId()).build();
        AirportResponse departureAirport = AirportResponse.builder().id(flightInstance.getDepartureAirportId()).build();
        AirportResponse arrivalAirport = AirportResponse.builder().id(flightInstance.getArrivalAirportId()).build();
        AircraftResponse aircraftResponse = AircraftResponse.builder().id(flightInstance.getFlight().getAircraftId()).build();

        return FlightInstanceMapper.toResponse(
                flightInstance,
                aircraftResponse,
                airline,
                departureAirport,
                arrivalAirport
        );
    }
}
