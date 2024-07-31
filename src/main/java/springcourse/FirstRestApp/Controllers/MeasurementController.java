package springcourse.FirstRestApp.Controllers;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springcourse.FirstRestApp.DTO.MeasurementDTO;
import springcourse.FirstRestApp.DTO.SensorDTO;
import springcourse.FirstRestApp.models.Measurement;
import springcourse.FirstRestApp.models.Sensor;
import springcourse.FirstRestApp.services.MeasurementService;
import springcourse.FirstRestApp.services.SensorService;
import springcourse.FirstRestApp.util.MeasurementErrorResponse;
import springcourse.FirstRestApp.util.MeasurementNotCreatedException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private  final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }
    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements(){

        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error :errors){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());

        }
        Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName());
        if (sensor == null) {
            sensor = new Sensor(measurementDTO.getSensor().getName());
            sensorService.save(sensor);
        }

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(sensor);
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> hadleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount(){
       return measurementService.countRainingDays();
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){

        return modelMapper.map(measurementDTO,Measurement.class);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(new SensorDTO(measurement.getSensor().getName()));
        return measurementDTO;
    }

}
