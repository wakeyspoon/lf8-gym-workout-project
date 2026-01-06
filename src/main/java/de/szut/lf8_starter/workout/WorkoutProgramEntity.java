package de.szut.lf8_starter.workout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "workout_program")    
public class WorkoutProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String programName;

    private String targetMuscleGroup;

    private String difficultyLevel;

    private int durationWeeks;

    private int sessionsPerWeek;

    public WorkoutProgramEntity(String programName, String targetMuscleGroup, String difficultyLevel, int durationWeeks, int sessionsPerWeek) {
        this.programName = programName;
        this.targetMuscleGroup = targetMuscleGroup;
        this.difficultyLevel = difficultyLevel;
        this.durationWeeks = durationWeeks;
        this.sessionsPerWeek = sessionsPerWeek;
    }
}
