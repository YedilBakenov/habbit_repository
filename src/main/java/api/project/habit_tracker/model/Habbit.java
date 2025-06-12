package api.project.habit_tracker.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "habbits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Habbit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Double duration;

    @Column(name = "created_at")
    private LocalDate createAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    public void createdAt(){
        createAt = LocalDate.now();
    }

    @PreUpdate
    public void updatedAt(){
        updatedAt = LocalDate.now();
    }

    @ManyToMany
    public List<User> users;
}
