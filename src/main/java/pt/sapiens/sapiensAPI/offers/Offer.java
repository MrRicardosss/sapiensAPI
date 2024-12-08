package pt.sapiens.sapiensAPI.offers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.applications.Application;
import pt.sapiens.sapiensAPI.categories.Category;
import pt.sapiens.sapiensAPI.municipalities.Municipality;
import pt.sapiens.sapiensAPI.organizations.Organization;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Organization organization;

    @OneToMany
    @JoinColumn
    private List<Application> applications;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipality municipality;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    @JsonIgnore
    private LocalDateTime updatedAt;
}