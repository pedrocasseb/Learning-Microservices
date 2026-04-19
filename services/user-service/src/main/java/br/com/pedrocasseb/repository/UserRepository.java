package br.com.pedrocasseb.repository;

import br.com.pedrocasseb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
