package de.szut.lf8_starter.workout;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutProgramService {

    private final WorkoutProgramRepository repository;

    public WorkoutProgramService(WorkoutProgramRepository repository) {
        this.repository = repository;
    }

    public WorkoutProgramEntity create(WorkoutProgramEntity entity) {
        return this.repository.save(entity);
    }

    public List<WorkoutProgramEntity> readAll() {
        return this.repository.findAll();
    }

    public WorkoutProgramEntity readById(long id) {
        Optional<WorkoutProgramEntity> optionalProgram = this.repository.findById(id);
        return optionalProgram.orElse(null);
    }

    public WorkoutProgramEntity update(long id, WorkoutProgramEntity updatedEntity) {
        Optional<WorkoutProgramEntity> existingProgram = this.repository.findById(id);
        if (existingProgram.isEmpty()) {
            return null;
        }
        updatedEntity.setId(id);
        return this.repository.save(updatedEntity);
    }

    public void delete(WorkoutProgramEntity entity) {
        this.repository.delete(entity);
    }

    public List<WorkoutProgramEntity> findByTargetMuscleGroup(String targetMuscleGroup) {
        return this.repository.findByTargetMuscleGroup(targetMuscleGroup);
    }

    public List<WorkoutProgramEntity> findByDifficultyLevel(String difficultyLevel) {
        return this.repository.findByDifficultyLevel(difficultyLevel);
    }
}
