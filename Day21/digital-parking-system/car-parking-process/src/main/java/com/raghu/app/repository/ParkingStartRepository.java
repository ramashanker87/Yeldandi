package com.raghu.app.repository;

import com.raghu.app.module.ParkingStart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingStartRepository extends JpaRepository<ParkingStart, Integer> {



    ParkingStart findByParkingNo(String parkingNumber);
}
