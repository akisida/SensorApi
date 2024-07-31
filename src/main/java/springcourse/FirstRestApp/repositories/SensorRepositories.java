package springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.FirstRestApp.models.Sensor;

@Repository
public interface SensorRepositories extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
