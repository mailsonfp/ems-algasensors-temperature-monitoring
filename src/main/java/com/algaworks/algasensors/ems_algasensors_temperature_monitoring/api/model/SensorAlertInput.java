package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model;

import lombok.Data;

@Data
public class SensorAlertInput {
    private Double minTemperature;
    private Double maxTemperature;
}
