package com.algaworks.algasensors.ems_algasensors_temperature_monitoring.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class TemperatureLogId {
    private UUID value;

    public TemperatureLogId(UUID value){
        this.value = value;
    }

    public TemperatureLogId(String value){
        this.value = UUID.fromString(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
