package pt.sapiens.sapiensAPI.municipalities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
