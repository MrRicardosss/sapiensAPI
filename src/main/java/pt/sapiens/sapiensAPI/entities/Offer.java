package pt.sapiens.sapiensAPI.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean open;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Organization organization;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Application> applications;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipality municipality;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
}