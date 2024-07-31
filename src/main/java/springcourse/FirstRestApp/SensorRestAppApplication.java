package springcourse.FirstRestApp;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springcourse.FirstRestApp.DTO.MeasurementDTO;
import springcourse.FirstRestApp.DTO.SensorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SensorRestAppApplication /*implements CommandLineRunner*/ {
	@Autowired
	private RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(SensorRestAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	/*public void run(String... args) throws Exception {
		String sensorName = "Test SensorTwo";
		String registerSensorUrl = "http://localhost:8080/sensors/registration";
		String addMeasurementUrl = "http://localhost:8080/measurements/add";
		String getMeasurementsUrl = "http://localhost:8080/measurements";

		// Register a new sensor
		SensorDTO sensorDTO = new SensorDTO(sensorName);
		ResponseEntity<String> sensorResponse = restTemplate.postForEntity(registerSensorUrl, sensorDTO, String.class);
		System.out.println("Sensor registration response: " + sensorResponse.getStatusCode());

		// Send 1000 random measurements
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			MeasurementDTO measurementDTO = new MeasurementDTO();
			measurementDTO.setValue(-30 + (60) * random.nextDouble()); // Random temperature between -30 and 30
			measurementDTO.setRaining(random.nextBoolean());
			measurementDTO.setSensor(sensorDTO);
			restTemplate.postForEntity(addMeasurementUrl, measurementDTO, String.class);
		}
		System.out.println("Sent 1000 random measurements");

		// Get all measurements
		ResponseEntity<MeasurementDTO[]> measurementsResponse = restTemplate.getForEntity(getMeasurementsUrl, MeasurementDTO[].class);
		MeasurementDTO[] measurements = measurementsResponse.getBody();
		if (measurements != null) {
			System.out.println("Number of measurements received: " + measurements.length);
			plotTemperatures(measurements);
		} else {
			System.out.println("No measurements received.");
		}
	}

	private void plotTemperatures(MeasurementDTO[] measurements) {
		List<Double> temperatures = new ArrayList<>();
		for (MeasurementDTO measurement : measurements) {
			temperatures.add(measurement.getValue());
		}

		XYChart chart = new XYChartBuilder().width(800).height(600).title("Temperature Measurements").xAxisTitle("Measurement Number").yAxisTitle("Temperature").build();

		chart.addSeries("Temperature", null, temperatures).setMarker(SeriesMarkers.NONE);

		new SwingWrapper<>(chart).displayChart();
	}*/
}
