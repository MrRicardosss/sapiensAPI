package pt.sapiens.sapiensAPI.applications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.offers.Offer;
import pt.sapiens.sapiensAPI.volunteers.Volunteer;

import java.time.LocalDateTime;

@Entity
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    @JsonIgnore
    private LocalDateTime updatedAt;
}