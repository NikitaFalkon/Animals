package Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SpringBootApplication
public class AnimalList implements CommandLineRunner{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    JDBWorker jdbWorker;
    List<Animal> animals = new ArrayList<Animal>();

    public static void main(String[] args) {
        SpringApplication.run(AnimalList.class);
    }
    @Override
    public void run(String... strings) throws Exception
    {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS animals(first_name VARCHAR(255), cost INTEGER)  ");
        Collections.addAll(animals, new Animal(100, "Sharik"), new Animal(200, "Murka"));
        animals.forEach(animal->jdbcTemplate.batchUpdate("INSERT INTO animals(first_name, cost) VALUES ('"+animal.getName()+"',"+animal.getCost()+")"));
        jdbWorker.newAnimal("Animal", 300);
        jdbWorker.dellAnimal("Animal");
        jdbWorker.getAllAnimals();
    }
}
