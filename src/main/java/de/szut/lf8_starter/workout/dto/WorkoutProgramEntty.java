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
public class WorkoutProgramEntty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String targetMuscleGroup;

    private String difficultyLevel;

    private int durationInWeeks;

    private int sessionsPerWeek;

    public entity WorkoutProgramEntty(String name, String targetMuscleGroup, String difficultyLevel, int durationInWeeks, int sessionsPerWeek) {
        this.name = name;
        this.targetMuscleGroup = targetMuscleGroup;
        this.difficultyLevel = difficultyLevel;
        this.durationInWeeks = durationInWeeks;
        this.sessionsPerWeek = sessionsPerWeek;
    }
}

