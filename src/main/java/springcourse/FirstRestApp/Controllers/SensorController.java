package springcourse.FirstRestApp.Controllers;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springcourse.FirstRestApp.DTO.SensorDTO;
import springcourse.FirstRestApp.models.Sensor;
import springcourse.FirstRestApp.services.SensorService;
import springcourse.FirstRestApp.util.SensorErrorResponse;
import springcourse.FirstRestApp.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private  final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors =bindingResult.getFieldErrors();
            for (FieldError error:errors)
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handlerException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }
    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor,SensorDTO.class);
    }
}
