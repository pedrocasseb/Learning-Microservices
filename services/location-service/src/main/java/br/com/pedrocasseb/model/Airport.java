package br.com.pedrocasseb.model;

import br.com.pedrocasseb.embeddable.Address;
import br.com.pedrocasseb.embeddable.GeoCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iata_code", nullable = false, length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone", length = 50)
    private ZoneId timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName() {
        if(city != null && city.getCountryCode() != null) {
            return name.toUpperCase() + "/" + city.getCityCode();
        }
        return name.toUpperCase();
    }
}
