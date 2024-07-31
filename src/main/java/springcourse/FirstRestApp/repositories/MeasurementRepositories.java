package springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springcourse.FirstRestApp.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementRepositories extends JpaRepository<Measurement, Integer> {
    @Query("SELECT m FROM Measurement m WHERE m.raining = true")
    public List<Measurement> findMeasurementsByRainingTrue();
}
