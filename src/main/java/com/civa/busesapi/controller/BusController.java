package com.civa.busesapi.controller;

import com.civa.busesapi.model.dto.BusDto;
import com.civa.busesapi.model.dto.request.BusRequest;
import com.civa.busesapi.model.payload.MessageResponse;
import com.civa.busesapi.service.IBusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "bus", description = "Operaciones relacionadas con los autobuses")
public class BusController {

    private final IBusService busService;

    @Autowired
    public BusController(IBusService busService) {
        this.busService = busService;
    }

    @PostMapping("/bus")
    public ResponseEntity<MessageResponse> create(@RequestBody BusRequest busRequest) {
        try {
            BusDto busDto = busService.create(busRequest);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Bus created successfully")
                    .object(busDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to create bus")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bus")
    public ResponseEntity<MessageResponse> getAll(Pageable pageable) {
        try {
            Page<BusDto> buses = busService.getAll(pageable);
            return ResponseEntity.ok(MessageResponse.builder()
                    .message("Successfully fetched all buses")
                    .object(buses)
                    .build());
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to fetch buses")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable Integer id) {
        try {
            BusDto busDto = busService.getById(id);
            return ResponseEntity.ok(MessageResponse.builder()
                    .message("Bus found")
                    .object(busDto)
                    .build());
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to fetch bus")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bus/{id}")
    public ResponseEntity<MessageResponse> update(@PathVariable Integer id, @RequestBody BusRequest busRequest) {
        try {
            BusDto updatedBus = busService.update(id, busRequest);
            return ResponseEntity.ok(MessageResponse.builder()
                    .message("Bus updated successfully")
                    .object(updatedBus)
                    .build());
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to update bus")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bus/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        try {
            busService.deleteById(id);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Bus deleted successfully")
                    .object(null)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to delete bus")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
