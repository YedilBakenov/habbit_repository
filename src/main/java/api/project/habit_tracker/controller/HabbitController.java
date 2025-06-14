package api.project.habit_tracker.controller;

import api.project.habit_tracker.dto.ErrorDto;
import api.project.habit_tracker.dto.HabbitDto;
import api.project.habit_tracker.mapper.HabbitMapper;
import api.project.habit_tracker.model.Habbit;
import api.project.habit_tracker.service.HabbitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "~Habbit-Controller~", description = "API для работы с трекером привычек")
@RequestMapping(value = "/api/trackers/")
@Validated
public class HabbitController {

    private final HabbitService habbitService;
    private final HabbitMapper habbitMapper;

    @GetMapping
    @Operation(summary = "Получение всех привычек с базы", description = "Получение всех привычек по параметрам name, duration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычки получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "title": "Validation failed",
                                "detail": "Incorrect data"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )

            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "title": "Internal error",
                                "detail": "Server error"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            })
    })
    public ResponseEntity<List<HabbitDto>> getAllHabbits(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double duration
            ){

            List<HabbitDto> habbits = habbitMapper.toListDto(habbitService.getAllHabbits(name, duration));

            return ResponseEntity.ok(habbits);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение одной привычки", description = "Получение привычки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка получена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "title": "Validation failed",
                                "detail": "Incorrect data"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )

            }),
            @ApiResponse(responseCode = "404", description = "Не найдено",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 404,
                                "title": "Habbit not found",
                                "detail": "Object not found"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "title": "Internal error",
                                "detail": "Server error"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            })
    })
    public ResponseEntity<HabbitDto> getHabbitById(@PathVariable long id){
        Habbit habbit = habbitService.getHabbitById(id);
        return ResponseEntity.ok(habbitMapper.toDto(habbit));
    }

    @PostMapping
    @Operation(summary = "Сохранение в базу", description = "Сохранение привычки в базу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Привычка сохранена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "title": "Validation failed",
                                "detail": "Incorrect data"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )

            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "title": "Internal error",
                                "detail": "Server error"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            })
    })
    public ResponseEntity<HabbitDto> saveHabbit(@Valid @RequestBody HabbitDto habbitDto){
        Habbit habbit1 = habbitService.saveHabbit(habbitMapper.toModel(habbitDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(habbitMapper.toDto(habbit1));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление ресурса", description = "обновление привычки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка обновлена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabbitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "title": "Validation failed",
                                "detail": "Incorrect data"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )

            }),
            @ApiResponse(responseCode = "404", description = "Не найдено",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 404,
                                "title": "Habbit not found",
                                "detail": "Object not found"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "title": "Internal error",
                                "detail": "Server error"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            })
    })
    public ResponseEntity<HabbitDto> updateHabbit(@PathVariable long id, @RequestBody @Valid HabbitDto habbitDto){
        Habbit habbit = habbitService.updateHabbit(id, habbitMapper.toModel(habbitDto));

        return ResponseEntity.ok(habbitMapper.toDto(habbit));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ресурса", description = "удаление привычки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Привычка удалена"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 400,
                                "title": "Validation failed",
                                "detail": "Incorrect data"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )

            }),
            @ApiResponse(responseCode = "404", description = "Не найдено",content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 404,
                                "title": "Habbit not found",
                                "detail": "Object not found"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                                "status": 500,
                                "title": "Internal error",
                                "detail": "Server error"
                            }
                            """),
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            })
    })
    public ResponseEntity<Void> deleteHabbit(@PathVariable long id){

        habbitService.deleteHabbitById(id);

        return ResponseEntity.noContent().build();
    }

}
