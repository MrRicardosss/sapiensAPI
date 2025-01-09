package pt.sapiens.sapiensAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pt.sapiens.sapiensAPI.enums.UserType;

import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @ColumnDefault("'https://dummyimage.com/500x500.png/cc0000/ffffff'")
    private String profilePicture = "https://dummyimage.com/500x500.png/cc0000/ffffff";

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType role;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp(source = SourceType.DB)
    @JsonIgnore
    private LocalDateTime updatedAt;

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}