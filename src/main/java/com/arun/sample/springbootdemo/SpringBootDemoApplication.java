package com.arun.sample.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

interface MoviesRepository extends JpaRepository<Movie, Long> {
}

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MoviesRepository moviesRepository) {
        return args -> {
            Arrays.asList("Hello", "Dear").forEach(s -> moviesRepository.save(new Movie(s)));
            moviesRepository.findAll()
                    .stream().forEach(e -> System.out.println(e.getName()));
        };
    }
}

@RestController
class ReservationController {
    MoviesRepository moviesRepository;

    @Autowired
    ReservationController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @RequestMapping("/movies")
    public Collection<Movie> movies() {
        return moviesRepository.findAll();

    }

    @RequestMapping("/addMovie")
    public Collection<Movie> addMovie(@RequestParam String name) {
         moviesRepository.save(new Movie(name));
        return moviesRepository.findAll();
    }


}


@Entity
class Movie {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
