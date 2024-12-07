package pt.sapiens.sapiensAPI.applications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.offers.Offer;
import pt.sapiens.sapiensAPI.volunteers.Volunteer;

import java.sql.Timestamp;

@Entity
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Offer offer;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
}