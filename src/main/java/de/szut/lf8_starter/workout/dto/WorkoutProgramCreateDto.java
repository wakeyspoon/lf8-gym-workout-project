package de.szut.lf8_starter.workout.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutProgramCreateDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String programName;

    @NotBlank
    @Size(max = 100)
    private String targetMuscleGroup;

    @NotBlank
    @Size(max = 50)
    private String difficultyLevel;

    @Min(value = 1)
    private int durationWeeks;

    @Min(value = 1)
    private int sessionsPerWeek;
}