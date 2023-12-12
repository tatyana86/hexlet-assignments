package exercise.model;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

// BEGIN
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String firstName;
    private String lastName;
}
// END
