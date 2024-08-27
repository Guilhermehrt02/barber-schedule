package com.unifei.barber_schedule.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDTO {
    private int id;
    private int clientId;
    private int barberId;
    private int serviceId;
    private String date;
    private String time;

}
