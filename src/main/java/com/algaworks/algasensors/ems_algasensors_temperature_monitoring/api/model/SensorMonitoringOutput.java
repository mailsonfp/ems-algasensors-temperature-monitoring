package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.api.model;

import com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.model.SensorId;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOutput {

    private TSID id;
    private Double lastTemperature;
    private OffsetDateTime updatedAt;
    private Boolean enabled;
}
