package de.szut.lf8_starter.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WorkoutProgramGetDto {

    private long id;

    private String programName;

    private String targetMuscleGroup;

    private String difficultyLevel;

    private int durationWeeks;

    private int sessionsPerWeek;
}