package br.com.pedrocasseb.model;

import br.com.pedrocasseb.enums.AircraftStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_aircraft")
@EntityListeners(AuditingEntityListener.class)
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, length = 50)
    private String manufacturer;

    @Column(name = "seating_capacity", nullable = false)
    private Integer seatingCapacity;

    @Column(name = "economy_seats")
    private Integer economySeats = 0;

    @Column(name = "premium_economy_seats")
    private Integer premiumEconomySeats = 0;

    @Column(name = "business_seats")
    private Integer businessSeats = 0;

    @Column(name = "first_class_seats")
    private Integer firstClassSeats = 0;

    @Column(name = "cruising_speed_kmh")
    private Integer cruisingSpeedKmh;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    @Column(name = "range_km")
    private Integer rangeKm;

    @Column(name = "max_altitude_ft")
    private Integer maxAltitudeFt;

    private LocalDate nextMaintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AircraftStatus status = AircraftStatus.ACTIVE;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @ManyToOne
    private Airline airline;

    @Column(name = "current_airport_id")
    private Long currentAiportId;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Integer getTotalSeats() {
        return economySeats + businessSeats + premiumEconomySeats + firstClassSeats;
    }

    public boolean isOperational() {
        return AircraftStatus.ACTIVE.equals(status)
                && Boolean.TRUE.equals(isAvailable);
    }

    public boolean requiresMaintenance() {
        return nextMaintenanceDate != null
                && nextMaintenanceDate.isBefore(LocalDate.now().plusWeeks(2));
    }
}
