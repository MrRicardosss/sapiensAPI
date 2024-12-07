package pt.sapiens.sapiensAPI.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
public class Category {
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