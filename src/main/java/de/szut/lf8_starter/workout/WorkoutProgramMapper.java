package de.szut.lf8_starter.workout;

import de.szut.lf8_starter.workout.dto.WorkoutProgramCreateDto;
import de.szut.lf8_starter.workout.dto.WorkoutProgramGetDto;
import org.springframework.stereotype.Service;

@Service
public class WorkoutProgramMapper {

    public WorkoutProgramGetDto mapToGetDto(WorkoutProgramEntity entity) {
        return new WorkoutProgramGetDto(
                entity.getId(),
                entity.getProgramName(),
                entity.getTargetMuscleGroup(),
                entity.getDifficultyLevel(),
                entity.getDurationWeeks(),
                entity.getSessionsPerWeek()
        );
    }

    public WorkoutProgramEntity mapCreateDtoToEntity(WorkoutProgramCreateDto dto) {
        var entity = new WorkoutProgramEntity();
        entity.setProgramName(dto.getProgramName());
        entity.setTargetMuscleGroup(dto.getTargetMuscleGroup());
        entity.setDifficultyLevel(dto.getDifficultyLevel());
        entity.setDurationWeeks(dto.getDurationWeeks());
        entity.setSessionsPerWeek(dto.getSessionsPerWeek());
        return entity;
    }
}