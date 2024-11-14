package com.civa.busesapi.service.impl;

import com.civa.busesapi.exception.ResourceNotFoundException;
import com.civa.busesapi.model.dto.BrandDto;
import com.civa.busesapi.model.dto.request.BrandRequest;
import com.civa.busesapi.model.entity.Brand;
import com.civa.busesapi.repository.BrandRepository;
import com.civa.busesapi.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements IBrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDto create(BrandRequest request) {
        Brand newBrand = Brand.builder()
                .name(request.getName())
                .build();

        Brand savedBrand = brandRepository.save(newBrand);
        return convertToDto(savedBrand);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Brand not found");
        }
        brandRepository.deleteById(id);
    }

    private BrandDto convertToDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
