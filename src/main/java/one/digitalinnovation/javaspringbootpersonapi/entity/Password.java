package one.digitalinnovation.javaspringbootpersonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "attendant_id")
    private Attendant attendant;


    @OneToMany
    @JoinTable(name = "password_productItems")
    private List<ProductItem> productItems;

    private LocalDate date;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String status;
}
