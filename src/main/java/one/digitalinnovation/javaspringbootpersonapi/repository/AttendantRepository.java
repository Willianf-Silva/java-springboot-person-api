package one.digitalinnovation.javaspringbootpersonapi.repository;

import one.digitalinnovation.javaspringbootpersonapi.entity.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

/*
O repository é criado para gerenciar as operações SQL e aproveitar as bibliotecas prontas do JPA
 */
public interface AttendantRepository extends JpaRepository<Attendant, Long> {
}
