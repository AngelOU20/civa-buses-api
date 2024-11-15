package com.civa.busesapi.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class MessageResponse implements Serializable {
    private String message;
    private Object object;
}
