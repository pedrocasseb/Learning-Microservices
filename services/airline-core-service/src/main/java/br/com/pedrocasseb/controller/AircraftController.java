package br.com.pedrocasseb.controller;

import br.com.pedrocasseb.model.Aircraft;
import br.com.pedrocasseb.payload.request.AircraftRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;
import br.com.pedrocasseb.payload.response.ApiResponse;
import br.com.pedrocasseb.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aircraft")
@RequiredArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(
            @Valid @RequestBody AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        AircraftResponse aircraft = aircraftService.createAircraft(aircraftRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraft);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponse> getAircraftById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> listAllAircrafts(
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(aircraftService.listAllAircraftsByOwnerId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft(
            @Valid @RequestBody AircraftRequest request,
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAircraft(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        aircraftService.deleteAircraft(id, userId);
        ApiResponse apiResponse = new ApiResponse("airline deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
