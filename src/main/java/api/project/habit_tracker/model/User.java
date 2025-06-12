package api.project.habit_tracker.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    private int age;

    private String email;

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



}
