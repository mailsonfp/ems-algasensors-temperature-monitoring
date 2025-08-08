package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.infrastructure.rabbitmq;

import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model.TemperatureLogData;
import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.service.SensorAlertService;
import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;
    private final SensorAlertService sensorAlertService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROCESS_TEMPERATURE, concurrency = "2-3")
    @SneakyThrows
    public void handleProcessingTemperature(@Payload TemperatureLogData temperatureLogData, @Headers Map<String, Object> headers){
        log.info("m=handleProcessingTemperature SensorId: {}, temperature: {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ALERTING, concurrency = "2-3")
    @SneakyThrows
    public void handleAlerting(@Payload TemperatureLogData temperatureLogData){
        sensorAlertService.handleAlert(temperatureLogData);
    }
}
