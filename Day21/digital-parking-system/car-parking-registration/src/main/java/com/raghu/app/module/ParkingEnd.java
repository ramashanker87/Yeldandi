package com.raghu.app.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParkingEnd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String parkingNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private  int price;
    private String status;
    private String registrationNumber;



}
