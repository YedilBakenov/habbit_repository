package api.project.habit_tracker.dto;


import api.project.habit_tracker.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "SchemaDto")
public class HabbitDto {

    private long id;
    private Double duration;
    private String name;
    private LocalDate create;
    private LocalDate update;
    private List<User> users;

}
