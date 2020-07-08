package one.digitalinnovation.javaspringbootpersonapi.repository;

import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    List<Password> findByStatus(String status);
}
