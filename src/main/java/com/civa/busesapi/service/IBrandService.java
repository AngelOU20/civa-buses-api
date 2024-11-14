package com.civa.busesapi.service;

import com.civa.busesapi.model.dto.BrandDto;
import com.civa.busesapi.model.dto.request.BrandRequest;
import com.civa.busesapi.model.entity.Bus;

import java.util.List;

public interface IBrandService {
    BrandDto create(BrandRequest request);
    List<BrandDto> getAll();
    void deleteById(Integer id);
}
