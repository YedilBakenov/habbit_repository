package api.project.habit_tracker.mapper;

import api.project.habit_tracker.dto.HabbitDto;
import api.project.habit_tracker.model.Habbit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabbitMapper {

    @Mapping(source = "createAt", target = "create")
    @Mapping(source = "updatedAt", target = "update")
    HabbitDto toDto(Habbit habbit);

    @Mapping(source = "create", target = "createAt")
    @Mapping(source = "update", target = "updatedAt")
    Habbit toModel(HabbitDto habbitDto);

    List<HabbitDto> toListDto(List<Habbit> habbitList);

    List<Habbit> toModelList(List<HabbitDto> habbitDtosList);

}
