package one.digitalinnovation.javaspringbootpersonapi.repository;

import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
