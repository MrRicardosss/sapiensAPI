package pt.sapiens.sapiensAPI.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Offer offer;
}