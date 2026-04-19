package br.com.pedrocasseb.mapper;

import br.com.pedrocasseb.model.Aircraft;
import br.com.pedrocasseb.model.Airline;
import br.com.pedrocasseb.payload.request.AircraftRequest;
import br.com.pedrocasseb.payload.response.AircraftResponse;

public class AircraftMapper {
    public static Aircraft toEntity(AircraftRequest aircraft, Airline airline) {
        if(aircraft == null || airline == null) return null;

        return Aircraft.builder()
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .businessSeats(aircraft.getBusinessSeats())
                .premiumEconomySeats(aircraft.getPremiumEconomySeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitudeFt())
                .yearOfManufacture(aircraft.getYearOfManufacture())
                .registrationDate(aircraft.getRegistrationDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getStatus())
                .isAvailable(aircraft.getIsAvailable())
                .airline(airline)
                .currentAiportId(aircraft.getCurrentAirportId())
                .build();
    }

    public static AircraftResponse toResponse(Aircraft aircraft) {
        if(aircraft == null) return null;

        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .businessSeats(aircraft.getBusinessSeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .premiumEconomySeats(aircraft.getPremiumEconomySeats())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitudeFt())
                .yearOfManufacture(aircraft.getYearOfManufacture())
                .registrationDate(aircraft.getRegistrationDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getStatus())
                .isAvailable(aircraft.getIsAvailable())
                .currentAirportId(aircraft.getCurrentAiportId())
                .totalSeats(aircraft.getTotalSeats())
                .requiresMaintenance(aircraft.requiresMaintenance())
                .isOperational(aircraft.isOperational())
                .airlineId(aircraft.getAirline() != null ? aircraft.getAirline().getId() : null)
                .airlineName(aircraft.getAirline() != null ? aircraft.getAirline().getName() : null)
                .airlineIataCode(aircraft.getAirline() != null ? aircraft.getAirline().getIataCode() : null)
                .createdAt(aircraft.getCreatedAt())
                .updatedAt(aircraft.getUpdatedAt())
                .build();
    }

    public static void updateEntity(Aircraft aircraft, AircraftRequest request) {
        if(aircraft == null || request == null) return;

        aircraft.setCode(request.getCode());
        aircraft.setModel(request.getModel());
        aircraft.setManufacturer(request.getManufacturer());
        aircraft.setSeatingCapacity(request.getSeatingCapacity());
        aircraft.setEconomySeats(request.getEconomySeats());
        aircraft.setBusinessSeats(request.getBusinessSeats());
        aircraft.setFirstClassSeats(request.getFirstClassSeats());
        aircraft.setPremiumEconomySeats(request.getPremiumEconomySeats());
        aircraft.setRangeKm(request.getRangeKm());
        aircraft.setMaxAltitudeFt(request.getMaxAltitudeFt());
        aircraft.setCruisingSpeedKmh(request.getCruisingSpeedKmh());
        aircraft.setYearOfManufacture(request.getYearOfManufacture());
        aircraft.setRegistrationDate(request.getRegistrationDate());
        aircraft.setNextMaintenanceDate(request.getNextMaintenanceDate());
        aircraft.setStatus(request.getStatus());
        aircraft.setIsAvailable(request.getIsAvailable());
        aircraft.setCurrentAiportId(request.getCurrentAirportId());
    }
}
