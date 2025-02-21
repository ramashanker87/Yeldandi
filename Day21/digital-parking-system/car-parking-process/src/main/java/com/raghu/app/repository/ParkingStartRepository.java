package com.raghu.app.repository;

import com.example.parking.model.ParkingStart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingStartRepository extends CrudRepository<ParkingStart, String> {
}