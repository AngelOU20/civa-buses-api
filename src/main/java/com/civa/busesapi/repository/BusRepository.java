package com.civa.busesapi.repository;

import com.civa.busesapi.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
}
