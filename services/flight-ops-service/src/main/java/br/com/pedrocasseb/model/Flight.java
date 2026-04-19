package br.com.pedrocasseb.model;

import br.com.pedrocasseb.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_flight")
@EntityListeners(AuditingEntityListener.class)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "airline_id", nullable = false)
    private Long airlineId;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraftId;

    @Column(name = "departure_airport_id", nullable = false)
    private Long departureAirportId;

    @Column(name = "arrival_airport_id", nullable = false)
    private Long arrivalAirportId;

    @Enumerated(EnumType.STRING)
    private FlightStatus status = FlightStatus.SCHEDULED;

    @CreatedDate
    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
