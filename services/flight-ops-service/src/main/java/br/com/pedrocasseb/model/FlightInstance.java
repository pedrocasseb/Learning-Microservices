package br.com.pedrocasseb.model;

import br.com.pedrocasseb.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_flight_instance")
@EntityListeners(AuditingEntityListener.class)
public class FlightInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airline_id")
    private Long airlineId;

    @ManyToOne
    private Flight flight;

    @Column(name = "departure_airport_id", nullable = false)
    private Long departureAirportId;

    @Column(name = "arrival_airport_id", nullable = false)
    private Long arrivalAirportId;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    @Column(name = "min_advance_booking_days")
    private Integer minAdvanceBookingDays;

    @Column(name = "max_advance_booking_days")
    private Integer maxAdvanceBookingDays;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Transient
    public String getFormatedDuration() {
        if(departureDateTime == null || arrivalDateTime == null) return null;
        Duration duration = Duration.between(departureDateTime, arrivalDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes();

        StringBuilder sb = new StringBuilder();
        if(hours > 0) sb.append(hours).append("h ");
        if(minutes > 0) sb.append(minutes).append("m ");
        return sb.toString().trim();
    }
}
