package br.com.pedrocasseb.payload.request;

import br.com.pedrocasseb.embeddable.Support;
import br.com.pedrocasseb.enums.AirlineStatus;
import br.com.pedrocasseb.payload.dto.UserDTO;
import br.com.pedrocasseb.payload.response.CityResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineRequest {

    @NotBlank(message = "iata code is mandatory")
    @Size(min = 3, max = 3, message = "iata code must be exactly 3 characters")
    private String iataCode;

    @NotBlank(message = "icao code is mandatory")
    @Size(min = 3, max = 3, message = "icao code must be exactly 3 characters")
    private String icaoCode;

    @NotBlank(message = "name is mandatory")
    private String name;

    private String alias;

    private String logoUrl;

    private String website;

    private AirlineStatus status;

    private String alliance;

    private Long headquartersCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;
}
