package com.algaworks.algasensors.ems_algasensors_temperature_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class EmsAlgasensorsTemperatureMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsAlgasensorsTemperatureMonitoringApplication.class, args);

		Integer number = 0;
		String text = "Mailson Fernando Pereira";
		BigDecimal decimal = BigDecimal.ZERO;

		System.out.println(number);
		System.out.println(text);
	}

}
