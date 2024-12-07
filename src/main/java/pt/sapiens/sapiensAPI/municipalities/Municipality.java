package pt.sapiens.sapiensAPI.municipalities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
}