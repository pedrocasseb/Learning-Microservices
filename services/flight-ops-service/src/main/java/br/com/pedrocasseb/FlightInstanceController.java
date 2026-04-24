package br.com.pedrocasseb;

import br.com.pedrocasseb.payload.request.FlightInstanceRequest;
import br.com.pedrocasseb.payload.response.ApiResponse;
import br.com.pedrocasseb.payload.response.FlightInstanceResponse;
import br.com.pedrocasseb.service.FlightInstanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/flight-instances")
@RequiredArgsConstructor
public class FlightInstanceController {
    private final FlightInstanceService flightInstanceService;

    @PostMapping
    public ResponseEntity<FlightInstanceResponse> createFlightInstance(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @Valid @RequestBody FlightInstanceRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(flightInstanceService.createFlightInstance(airlineId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> getFlightInstanceById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(flightInstanceService.getFlightInstanceById(id));
    }

    @GetMapping
    public ResponseEntity<Page<FlightInstanceResponse>> getByAirlineId(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long flightId,
            @RequestParam(required = false)LocalDate onDate,
            Pageable pageable
            ) {
        return ResponseEntity.ok(flightInstanceService.getByAirlineId(
                airlineId,
                departureAirportId,
                arrivalAirportId,
                flightId,
                onDate,
                pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> updateFlightInstance(
            @PathVariable Long id,
            @RequestBody FlightInstanceRequest request
    ) throws Exception {
        return ResponseEntity.ok(flightInstanceService.updateFlightInstance(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightInstance(
            @PathVariable Long id
    ) throws Exception {
        flightInstanceService.deleteFlightInstance(id);
        ApiResponse res = new ApiResponse("flight instance deleted successfully");
        return ResponseEntity.ok(res);
    }
}
