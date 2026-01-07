package de.szut.lf8_starter.config;


import de.szut.lf8_starter.hello.HelloEntity;
import de.szut.lf8_starter.hello.HelloRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import de.szut.lf8_starter.workout.WorkoutProgramEntity;
import de.szut.lf8_starter.workout.WorkoutProgramRepository;

@Component
public class SampleDataCreator implements ApplicationRunner {

    private HelloRepository repository;
    private WorkoutProgramRepository workoutProgramRepository;


    public SampleDataCreator(HelloRepository repository, WorkoutProgramRepository workoutProgramRepository) {
        this.repository = repository;
        this.workoutProgramRepository = workoutProgramRepository;
    }

    public void run(ApplicationArguments args) {
        repository.save(new HelloEntity("Hallo Welt!"));
        repository.save(new HelloEntity("Schöner Tag heute"));
        repository.save(new HelloEntity("FooBar"));

        workoutProgramRepository.save(new WorkoutProgramEntity(
        "Starting Strength", "Full Body", "Beginner", 12, 3));
        workoutProgramRepository.save(new WorkoutProgramEntity(
        "PPL Hypertrophy", "Full Body", "Intermediate", 16, 6));
        workoutProgramRepository.save(new WorkoutProgramEntity(
        "German Volume Training", "Full Body", "Advanced", 6, 4));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
