package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertOutput {
    private TSID id;
    private Double maxTemperature;
    private Double minTemperature;
}
