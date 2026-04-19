package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.mapper.AircraftMapper;
import br.com.pedrocasseb.model.Aircraft;
import br.com.pedrocasseb.model.Airline;
import br.com.pedrocasseb.payload.request.AircraftRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;
import br.com.pedrocasseb.repository.AircraftRepository;
import br.com.pedrocasseb.repository.AirlineRepository;
import br.com.pedrocasseb.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("airline not exists for this owner id")
        );

        Aircraft aircraft = AircraftMapper.toEntity(request, airline);

        if (aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new Exception("aircraft code already exists");
        }

        if (aircraft.getSeatingCapacity() < aircraft.getTotalSeats()) {
            throw new Exception("aircraft seating capacity exceeded");
        }

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception {
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(
                () -> new Exception("Aircraft not exist with id")
        );

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAircraftsByOwnerId(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("this owner dont have airline")
        );

        return aircraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AircraftMapper::toResponse)
                .toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("this owner dont have airline")
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null) throw new Exception("aircraft not exist");

        if (request.getCode() != null && !aircraft.getCode().equals(request.getCode()) &&
                aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new Exception("aircraft code already exists");
        }

        AircraftMapper.updateEntity(aircraft, request);

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("this owner dont have airline")
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null) throw new Exception("aircraft not exist");

        aircraftRepository.delete(aircraft);
    }
}
