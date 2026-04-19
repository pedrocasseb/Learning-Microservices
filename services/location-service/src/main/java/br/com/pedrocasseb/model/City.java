package br.com.pedrocasseb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "city_code", nullable = false, unique = true)
    private String cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "region_code")
    @Size(max = 10)
    private String regionCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZoneId;
}
