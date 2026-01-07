package de.szut.lf8_starter.workout;

import de.szut.lf8_starter.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_starter.workout.dto.WorkoutProgramCreateDto;
import de.szut.lf8_starter.workout.dto.WorkoutProgramGetDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/workout-programs")
public class WorkoutProgramController {
    
    private final WorkoutProgramService service;
    private final WorkoutProgramMapper mapper;

    public WorkoutProgramController(WorkoutProgramService service, WorkoutProgramMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutProgramGetDto create(@RequestBody @Valid WorkoutProgramCreateDto dto) {
        WorkoutProgramEntity entity = this.mapper.mapCreateDtoToEntity(dto);
        entity = this.service.create(entity);
        return this.mapper.mapToGetDto(entity);
    }

    @GetMapping
    public List<WorkoutProgramGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.mapper.mapToGetDto(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public WorkoutProgramGetDto findById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("WorkoutProgramEntity not found on id = " + id);
        }
        return this.mapper.mapToGetDto(entity);
    }

    @PutMapping("/{id}")
    public WorkoutProgramGetDto update(@PathVariable long id, 
                                       @RequestBody @Valid WorkoutProgramCreateDto dto) {
        WorkoutProgramEntity entity = this.mapper.mapCreateDtoToEntity(dto);
        entity = this.service.update(id, entity);
        if (entity == null) {
            throw new ResourceNotFoundException("Workout program not found with id = " + id);
        }
        return this.mapper.mapToGetDto(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Workout program not found with id = " + id);
        }
        this.service.delete(entity);
    }

    @GetMapping("/findByMuscleGroup")
    public List<WorkoutProgramGetDto> findByTargetMuscleGroup(@RequestParam String targetMuscleGroup) {
        return this.service
                .findByTargetMuscleGroup(targetMuscleGroup)
                .stream()
                .map(this.mapper::mapToGetDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findByDifficulty")
    public List<WorkoutProgramGetDto> findByDifficultyLevel(@RequestParam String difficultyLevel) {
        return this.service
                .findByDifficultyLevel(difficultyLevel)
                .stream()
                .map(this.mapper::mapToGetDto)
                .collect(Collectors.toList());
    }
}