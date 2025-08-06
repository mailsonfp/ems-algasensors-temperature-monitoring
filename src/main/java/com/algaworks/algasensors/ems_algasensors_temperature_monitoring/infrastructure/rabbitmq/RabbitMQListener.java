package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.infrastructure.rabbitmq;

import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model.TemperatureLogData;
import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
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

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handle(@Payload TemperatureLogData temperatureLogData, @Headers Map<String, Object> headers){
        log.info("SensorId: {}, temperature: {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
    }
}
