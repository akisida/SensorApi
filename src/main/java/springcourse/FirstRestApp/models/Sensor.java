package springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Name can't be empty")
    @Size(min=3, max=50, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
    public Sensor() {}
    public Sensor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
