package br.com.pedrocasseb.controller;

import br.com.pedrocasseb.enums.AirlineStatus;
import br.com.pedrocasseb.payload.request.AirlineRequest;
import br.com.pedrocasseb.payload.response.AirlineDropdownItem;
import br.com.pedrocasseb.payload.response.AirlineResponse;
import br.com.pedrocasseb.payload.response.ApiResponse;
import br.com.pedrocasseb.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(
            @Valid @RequestBody AirlineRequest airlineRequest,
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                airlineService.createAirline(airlineRequest, userId)
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwner(
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineByOwner(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable) {
        return ResponseEntity.ok(airlineService.getAllAirlines(pageable));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropdownItem>> getAirlineDropdown() {
        return ResponseEntity.ok(airlineService.getAirlineDropdown());
    }

    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(
            @Valid @RequestBody AirlineRequest request,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(airlineService.updateAirline(request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirline(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        airlineService.deleteAirline(id, userId);
        ApiResponse apiResponse = new ApiResponse("airline deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE));
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE));
    }

    @PostMapping("/{id}/ban")
    public ResponseEntity<AirlineResponse> banAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED));
    }

}
