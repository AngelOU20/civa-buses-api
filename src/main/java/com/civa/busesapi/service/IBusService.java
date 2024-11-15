package com.civa.busesapi.service;

import com.civa.busesapi.model.dto.BusDto;
import com.civa.busesapi.model.dto.request.BusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBusService {
    BusDto create(BusRequest request);
    Page<BusDto> getAll(Pageable pageable);
    BusDto getById(Integer id);
    BusDto update(Integer id, BusRequest request);
    void deleteById(Integer id);
}
