package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.mapper.AirportMapper;
import br.com.pedrocasseb.model.Airport;
import br.com.pedrocasseb.model.City;
import br.com.pedrocasseb.payload.request.AirportRequest;
import br.com.pedrocasseb.payload.response.AirportResponse;
import br.com.pedrocasseb.repository.AirportRepository;
import br.com.pedrocasseb.repository.CityRepository;
import br.com.pedrocasseb.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {
        if(airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA Code already exists");
        }

        City city = cityRepository.findById(request.getCityId()).orElseThrow(
                ()-> new Exception("City not found")
        );

        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new Exception("Airport not exists with provided id")
        );
        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) throws Exception {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(
                ()-> new Exception("Aiport not exists with id" + id)
        );

        if(request.getIataCode() != null
                && !existingAirport.getIataCode().equals(request.getIataCode())
                && airportRepository.findByIataCode(request.getIataCode()).isPresent()
        ) {
            throw new Exception("Airport with IATA Code already exists");
        }

        AirportMapper.updateEntity(request, existingAirport);
        Airport updatedAirport = airportRepository.save(existingAirport);
        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new Exception("Airport not exists with provided id")
        );
        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
