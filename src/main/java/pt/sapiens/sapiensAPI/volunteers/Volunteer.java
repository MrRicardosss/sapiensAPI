package pt.sapiens.sapiensAPI.volunteers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import pt.sapiens.sapiensAPI.applications.Application;
import pt.sapiens.sapiensAPI.auth.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @ColumnDefault("'https://dummyimage.com/500x500.png/cc0000/ffffff'")
    private String profilePicture = "https://dummyimage.com/500x500.png/cc0000/ffffff";

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String civilId;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private User user;

    @OneToMany
    private List<Application> applications;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    @JsonIgnore
    private LocalDateTime updatedAt;
}