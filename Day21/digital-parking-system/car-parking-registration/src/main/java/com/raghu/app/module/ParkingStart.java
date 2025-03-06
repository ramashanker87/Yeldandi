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
public class ParkingStart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String parkingNo;

    private LocalDateTime startTime;
    private String status;
    private String registrationNo;


}
