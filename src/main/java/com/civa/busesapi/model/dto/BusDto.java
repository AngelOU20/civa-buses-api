package com.civa.busesapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {
    private Integer id;
    private String number;
    private String plate;
    private LocalDateTime creationDate;
    private String features;
    private BrandDto brand;
    @JsonProperty("isActive")
    private boolean isActive;
}
