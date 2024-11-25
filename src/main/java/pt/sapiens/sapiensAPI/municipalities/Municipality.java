package pt.sapiens.sapiensAPI.municipalities;

import jakarta.persistence.*;

@Entity
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
