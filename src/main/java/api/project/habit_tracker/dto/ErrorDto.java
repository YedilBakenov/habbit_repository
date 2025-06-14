package api.project.habit_tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Объект ошибки")
public class ErrorDto {

    @Schema(example = "400", description = "Код ошибки")
    private int code;

    @Schema(example = "Неверные данные", description = "Краткое описание ошибки")
    private String title;

    @Schema(example = "Поле id неверное", description = "Подробности ошибки")
    private String detail;

}
