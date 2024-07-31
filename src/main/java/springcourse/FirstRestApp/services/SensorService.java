package springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.FirstRestApp.models.Sensor;
import springcourse.FirstRestApp.repositories.SensorRepositories;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepositories sensorRepositories;;
    @Autowired
    public SensorService(SensorRepositories sensorRepositories) {
        this.sensorRepositories = sensorRepositories;
    }
    public List<Sensor> findAll() {return sensorRepositories.findAll();}

    @Transactional
    public void save(Sensor sensor) {
        sensor.setCreatedAt(new Date());
        sensorRepositories.save(sensor);
    }
    public Sensor findByName(String name){
        return sensorRepositories.findByName(name);
    }
}
