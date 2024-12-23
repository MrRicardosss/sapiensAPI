package pt.sapiens.sapiensAPI.organizations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.users.User;
import pt.sapiens.sapiensAPI.offers.Offer;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String website;

    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false, unique = true)
    private User user;

    @OneToMany
    @JoinColumn
    private List<Offer> offers;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    @JsonIgnore
    private LocalDateTime updatedAt;
}