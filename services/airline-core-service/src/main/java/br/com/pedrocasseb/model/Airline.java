package br.com.pedrocasseb.model;

import br.com.pedrocasseb.embeddable.Support;
import br.com.pedrocasseb.enums.AirlineStatus;
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
@Table(name = "tb_airline")
@EntityListeners(AuditingEntityListener.class)
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iata_code", nullable = false, unique = true)
    private String iataCode;

    @Column(name = "icao_code", nullable = false, unique = true)
    private String icaoCode;

    @Column(nullable = false)
    private String name;

    private String alias;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "logo_url")
    private String logoUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    private AirlineStatus status =  AirlineStatus.ACTIVE;

    private String alliance;

    @Embedded
    private Support support;

    @Column(name = "headquartes_city_id")
    private Long headquartersCityId;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @CreatedDate
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


}
