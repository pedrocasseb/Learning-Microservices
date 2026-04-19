package br.com.pedrocasseb.mapper;

import br.com.pedrocasseb.embeddable.Support;
import br.com.pedrocasseb.model.Airline;
import br.com.pedrocasseb.payload.request.AirlineRequest;
import br.com.pedrocasseb.payload.response.AirlineResponse;

public class AirlineMapper {
    public static Airline toEntity(AirlineRequest request, Long ownerId) {
        if (request == null) return null;

        Airline airline = Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .name(request.getName())
                .alias(request.getAlias())
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus())
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                .ownerId(ownerId)
                .build();

        if (request.getSupportEmail() != null
                || request.getSupportHours() != null
                || request.getSupportPhone() != null
        ) {
            airline.setSupport(
                    Support.builder()
                            .email(request.getSupportEmail())
                            .hours(request.getSupportHours())
                            .phone(request.getSupportPhone())
                            .build()
            );
        }

        return airline;
    }

    public static AirlineResponse toResponse(Airline airline) {
        if (airline == null) return null;

        return AirlineResponse.builder()
                .id(airline.getId())
                .name(airline.getName())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();
    }

    public static void updateEntity(AirlineRequest request, Airline airline) {
        if (request == null || airline == null) return;

        airline.setName(request.getName());
        airline.setIataCode(request.getIataCode());
        airline.setIcaoCode(request.getIcaoCode());
        airline.setAlias(request.getAlias());
        airline.setLogoUrl(request.getLogoUrl());
        airline.setWebsite(request.getWebsite());
        airline.setStatus(request.getStatus());
        airline.setAlliance(request.getAlliance());
        airline.setHeadquartersCityId(request.getHeadquartersCityId());

        if(airline.getSupport() != null){
            airline.setSupport(new Support());
        }

        airline.getSupport().setEmail(request.getSupportEmail());
        airline.getSupport().setHours(request.getSupportHours());
        airline.getSupport().setPhone(request.getSupportPhone());
    }
}
