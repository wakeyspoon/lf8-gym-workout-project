package de.szut.lf8_starter.workout;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgramEntity, Long> {

    List<WorkoutProgramEntity> findByTargetMuscleGroup(String targetMuscleGroup);
    List<WorkoutProgramEntity> findByDifficultyLevel(String difficultyLevel);
}