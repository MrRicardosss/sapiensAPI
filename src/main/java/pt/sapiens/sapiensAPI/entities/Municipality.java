package pt.sapiens.sapiensAPI.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}