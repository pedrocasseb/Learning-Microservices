package br.com.pedrocasseb.service;

import br.com.pedrocasseb.enums.AirlineStatus;
import br.com.pedrocasseb.payload.request.AirlineRequest;
import br.com.pedrocasseb.payload.response.AirlineDropdownItem;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest request, Long ownerId);
    AirlineResponse getAirlineByOwner(Long ownerId) throws Exception;
    AirlineResponse getAirlineById(Long id) throws Exception;
    Page<AirlineResponse> getAllAirlines(Pageable pageable);
    AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception;
    void deleteAirline(Long id, Long ownerId) throws Exception;

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception;

    List<AirlineDropdownItem> getAirlineDropdown();
}
