package br.com.pedrocasseb.service;

import br.com.pedrocasseb.payload.request.AircraftRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;

import java.util.List;

public interface AircraftService {
    AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception;
    AircraftResponse getAircraftById(Long id) throws Exception;
    List<AircraftResponse> listAllAircraftsByOwnerId(Long ownerId) throws Exception;
    AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception;
    void deleteAircraft(Long id, Long ownerId) throws Exception;
}
