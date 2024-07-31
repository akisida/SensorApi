package springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.FirstRestApp.models.Measurement;
import springcourse.FirstRestApp.repositories.MeasurementRepositories;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;

    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories) {
        this.measurementRepositories = measurementRepositories;
    }

    public List<Measurement> findAll()
    {
        return measurementRepositories.findAll();
    }
    @Transactional(readOnly = false)
    public void  save(Measurement measurement){
        measurement.setCreated_at(new Date());
        measurementRepositories.save(measurement);
    }

    public int countRainingDays(){
        return (int) measurementRepositories.findMeasurementsByRainingTrue().stream().count();
    }
}
