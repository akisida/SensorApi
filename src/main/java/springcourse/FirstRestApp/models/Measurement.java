package springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import javax.xml.crypto.Data;
import java.util.Date;


@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value shouldn't be empty")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "created_at")
    private Date created_at;
    public Measurement() {
    }
    public Measurement(double value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
