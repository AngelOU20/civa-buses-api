package com.civa.busesapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {
    private String number;
    private String plate;
    private String features;
    private Integer brandId;
    @JsonProperty("isActive")
    private boolean isActive;
}
