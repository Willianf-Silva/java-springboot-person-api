package one.digitalinnovation.javaspringbootpersonapi.repository;

import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
