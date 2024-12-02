package pt.sapiens.sapiensAPI.offers;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.categories.Category;
import pt.sapiens.sapiensAPI.municipalities.Municipality;
import pt.sapiens.sapiensAPI.organizations.Organization;

import java.util.Date;

@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipality municipality;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean open;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
}