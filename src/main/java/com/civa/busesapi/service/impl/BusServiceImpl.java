package com.civa.busesapi.service.impl;

import com.civa.busesapi.exception.ResourceNotFoundException;
import com.civa.busesapi.model.dto.BrandDto;
import com.civa.busesapi.model.dto.BusDto;
import com.civa.busesapi.model.dto.request.BusRequest;
import com.civa.busesapi.model.entity.Brand;
import com.civa.busesapi.model.entity.Bus;
import com.civa.busesapi.repository.BrandRepository;
import com.civa.busesapi.repository.BusRepository;
import com.civa.busesapi.service.IBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BusServiceImpl implements IBusService {

    private final BusRepository busRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, BrandRepository brandRepository) {
        this.busRepository = busRepository;
        this.brandRepository = brandRepository;
    }

    @Transactional
    @Override
    public BusDto create(BusRequest request) {
        log.info("Creating new Bus with request: {}", request);

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        Bus newBus = Bus.builder()
                .number(request.getNumber())
                .plate(request.getPlate())
                .features(request.getFeatures())
                .brand(brand)
                .isActive(request.isActive())
                .build();

        log.info("New Bus details before saving: {}", newBus);
        Bus savedBus = busRepository.save(newBus);
        log.info("Bus saved successfully with ID: {}", savedBus.getId());

        return convertToDto(savedBus);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BusDto> getAll(Pageable pageable) {
        log.info("Fetching all Buses with pageable: {}", pageable);
        Page<BusDto> buses = busRepository.findAll(pageable).map(this::convertToDto);
        log.info("Total Buses fetched: {}", buses.getTotalElements());
        return buses;
    }

    @Transactional(readOnly = true)
    @Override
    public BusDto getById(Integer id) {
        log.info("Fetching Bus by ID: {}", id);
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        log.info("Bus found: {}", bus);
        return convertToDto(bus);
    }

    @Transactional
    @Override
    public BusDto update(Integer id, BusRequest request) {
        log.info("Updating Bus with ID: {} and request: {}", id, request);
        Bus busToUpdate = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        busToUpdate.setNumber(request.getNumber());
        busToUpdate.setPlate(request.getPlate());
        busToUpdate.setFeatures(request.getFeatures());
        busToUpdate.setBrand(brand);
        busToUpdate.setActive(request.isActive());

        log.info("Bus details after update before saving: {}", busToUpdate);
        Bus updatedBus = busRepository.save(busToUpdate);
        log.info("Bus updated successfully with ID: {}", updatedBus.getId());

        return convertToDto(updatedBus);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        log.info("Deleting Bus by ID: {}", id);
        if (!busRepository.existsById(id)) {
            log.error("Bus with ID: {} not found", id);
            throw new ResourceNotFoundException("Bus not found");
        }
        busRepository.deleteById(id);
        log.info("Bus deleted successfully with ID: {}", id);
    }

    private BusDto convertToDto(Bus bus) {
        log.info("Converting Bus to BusDto: {}", bus);
        BrandDto brandDto = BrandDto.builder()
                .id(bus.getBrand().getId())
                .name(bus.getBrand().getName())
                .build();

        BusDto busDto = BusDto.builder()
                .id(bus.getId())
                .number(bus.getNumber())
                .plate(bus.getPlate())
                .creationDate(bus.getCreationDate())
                .features(bus.getFeatures())
                .brand(brandDto)
                .isActive(bus.isActive())
                .build();

        log.info("Conversion to BusDto complete: {}", busDto);
        return busDto;
    }
}
