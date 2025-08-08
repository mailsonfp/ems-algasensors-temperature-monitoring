package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.service;

import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model.TemperatureLogData;
import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.model.SensorId;
import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.repository.SensorAlertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorAlertService {

    private final SensorAlertRepository sensorAlertRepository;

    @Transactional
    public void handleAlert(TemperatureLogData temperatureLogData){
        sensorAlertRepository.findById( new SensorId(temperatureLogData.getSensorId()))
                .ifPresentOrElse(alert -> {
                            if (alert.getMaxTemperature() != null && temperatureLogData.getValue().compareTo(alert.getMaxTemperature()) >= 0)
                                log.info("Alert Max Temperature, SensorId: {}, temperature: {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
                            else if (alert.getMinTemperature() != null && temperatureLogData.getValue().compareTo(alert.getMinTemperature()) >= 0)
                                log.info("Alert Min Temperature, SensorId: {}, temperature: {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
                            else ignoredLog(temperatureLogData);
                        }, ()-> ignoredLog(temperatureLogData)
                );
    }

    private void ignoredLog(TemperatureLogData temperatureLogData) {
        log.info("Alert ignored SensorId: {}, temperature: {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
    }
}
