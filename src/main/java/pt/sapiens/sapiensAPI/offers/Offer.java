package pt.sapiens.sapiensAPI.offers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.applications.Application;
import pt.sapiens.sapiensAPI.categories.Category;
import pt.sapiens.sapiensAPI.municipalities.Municipality;
import pt.sapiens.sapiensAPI.organizations.Organization;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
}