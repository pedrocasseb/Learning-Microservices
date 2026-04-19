package br.com.pedrocasseb.repository;

import br.com.pedrocasseb.enums.AirlineStatus;
import br.com.pedrocasseb.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByOwnerId(Long ownerId);
    List<Airline> findAllByStatus(AirlineStatus status);
}
