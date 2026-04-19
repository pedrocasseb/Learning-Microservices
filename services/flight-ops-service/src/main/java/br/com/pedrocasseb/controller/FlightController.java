package br.com.pedrocasseb.controller;

import br.com.pedrocasseb.enums.FlightStatus;
import br.com.pedrocasseb.payload.request.FlightRequest;
import br.com.pedrocasseb.payload.response.ApiResponse;
import br.com.pedrocasseb.payload.response.FlightResponse;
import br.com.pedrocasseb.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(
            @Valid @RequestBody FlightRequest request,
            @RequestHeader("Airline-Id") Long airlineId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                flightService.createFlight(airlineId, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/airline")
    public ResponseEntity<Page<FlightResponse>> getAllFlightsByAirline(
            @RequestHeader("Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(flightService.getFlightsByAirlineId(airlineId,
                departureAirportId, arrivalAirportId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlightById(@PathVariable Long id, @RequestBody FlightRequest request) throws Exception {
        return ResponseEntity.ok(flightService.updateFlight(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FlightResponse> updateFlightStatus(@PathVariable Long id, @RequestBody FlightStatus status) throws Exception {
        return ResponseEntity.ok(flightService.changeStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlight(
            @PathVariable Long id,
            @RequestHeader("Airline-Id") Long airlineId
            ) throws Exception {
        flightService.deleteFlight(airlineId,id);
        return ResponseEntity.ok(new ApiResponse("Flight deleted"));
    }
}
