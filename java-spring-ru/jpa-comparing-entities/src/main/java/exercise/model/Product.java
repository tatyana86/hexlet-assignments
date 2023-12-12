package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Table(name = "products")
@EqualsAndHashCode(of = {"price", "title"})
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer price;
    private String title;
}
// END
