package br.com.pedrocasseb.payload.request;

import br.com.pedrocasseb.enums.AircraftStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftRequest {

    @NotBlank(message = "code is required")
    private String code;

    @NotBlank(message = "model is required")
    private String model;

    @NotBlank(message = "manufacturer is required")
    private String manufacturer;

    @NotNull(message = "seating capacity is required")
    @Positive(message = "seating capacity must be positive")
    private Integer seatingCapacity;

    @Positive(message = "economy seats must be positive")
    private Integer economySeats;

    @Positive(message = "premium economy seats must be positive")
    private Integer premiumEconomySeats;

    @Positive(message = "business seats must be positive")
    private Integer businessSeats;

    @Positive(message = "first class seats must be positive")
    private Integer firstClassSeats;

    @Positive(message = "range must be positive")
    private Integer rangeKm;

    @Positive(message = "cruising speed must be positive")
    private Integer cruisingSpeedKmh;

    @Positive(message = "maximum altitude must be positive")
    private Integer maxAltitudeFt;

    @Positive(message = "year of manufacture must be positive")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    private LocalDate nextMaintenanceDate;

    private AircraftStatus status;

    private Boolean isAvailable = false;

    private Long currentAirportId;
}
