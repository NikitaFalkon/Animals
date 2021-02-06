package Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBWorker {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void getAllAnimals()
    {
        jdbcTemplate.query("SELECT first_name, cost FROM animals", (rs, rowNum) -> new Animal(rs.getInt("cost"), rs.getString("first_name")))
                .forEach(animal-> System.out.println(animal.getCost()+" "+animal.getName()));
    }
    public void getAnimalCost(String name)
    {

        jdbcTemplate.query("SELECT first_name, cost FROM animals WHERE first_name = '"+name+"'", (rs, rowNum) -> new Animal(rs.getInt("cost"), rs.getString("first_name")))
                .forEach(animal-> System.out.println(animal.getCost()));
    }
    public void newAnimal(String name, int cost)
    {
        jdbcTemplate.batchUpdate("INSERT INTO animals(first_name, cost) VALUES('"+name+"', "+cost+")");
    }
    public void dellAnimal(String name)
    {
        jdbcTemplate.batchUpdate("DELETE FROM animals WHERE first_name= '"+name+"'");
    }
}
