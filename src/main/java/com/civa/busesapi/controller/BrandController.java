package com.civa.busesapi.controller;

import com.civa.busesapi.model.dto.BrandDto;
import com.civa.busesapi.model.dto.request.BrandRequest;
import com.civa.busesapi.model.payload.MessageResponse;
import com.civa.busesapi.service.IBrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "marca", description = "Operaciones relacionadas con las marcas")
public class BrandController {
    private final IBrandService brandService;

    @Autowired
    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ResponseEntity<MessageResponse> getAll() {
        try {
            List<BrandDto> brands = brandService.getAll();
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Successfully fetched all brands")
                    .object(brands)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to fetch brands")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/brands")
    public ResponseEntity<MessageResponse> create(@RequestBody BrandRequest brandRequest) {
        try {
            BrandDto createdBrand = brandService.create(brandRequest);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Brand created successfully")
                    .object(createdBrand)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to create brand")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        try {
            brandService.deleteById(id);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Brand deleted successfully")
                    .object(null)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Failed to delete brand")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
