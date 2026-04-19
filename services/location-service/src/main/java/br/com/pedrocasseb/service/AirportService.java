package br.com.pedrocasseb.service;

import br.com.pedrocasseb.payload.request.AirportRequest;
import br.com.pedrocasseb.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest request) throws Exception;
    AirportResponse getAirportById(Long id) throws Exception;

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id, AirportRequest request) throws Exception;
    void deleteAirport(Long id) throws Exception;
    List<AirportResponse> getAirportsByCityId(Long cityId);
}
