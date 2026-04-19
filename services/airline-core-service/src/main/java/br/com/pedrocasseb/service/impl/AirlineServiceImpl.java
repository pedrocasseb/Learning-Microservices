package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.enums.AirlineStatus;
import br.com.pedrocasseb.mapper.AirlineMapper;
import br.com.pedrocasseb.model.Airline;
import br.com.pedrocasseb.payload.request.AirlineRequest;
import br.com.pedrocasseb.payload.response.AirlineDropdownItem;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import br.com.pedrocasseb.repository.AirlineRepository;
import br.com.pedrocasseb.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        Airline airline = AirlineMapper.toEntity(request, ownerId);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("airline not found with owner id " + ownerId)
        );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws Exception {
        Airline airline = airlineRepository.findById(id).orElseThrow(
                () -> new Exception("airline not found with id " + id)
        );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return airlineRepository.findAll(pageable).map(AirlineMapper::toResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("airline not found with owner id " + ownerId)
        );
        AirlineMapper.updateEntity(request, airline);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new Exception("airline not found with owner id " + ownerId)
        );
        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(
                () -> new Exception("airline not found with id " + airlineId)
        );
        airline.setStatus(status);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
        return airlineRepository.findAllByStatus(AirlineStatus.ACTIVE)
                .stream()
                .map(a -> AirlineDropdownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .iataCode(a.getIataCode())
                        .icaoCode(a.getIcaoCode())
                        .logoUrl(a.getLogoUrl())
                        .build()).toList();
    }
}
