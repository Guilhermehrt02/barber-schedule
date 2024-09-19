package com.unifei.barber_schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceDTO {

    private int id;
    private String name;
    private int duration;
    private double price;

}
