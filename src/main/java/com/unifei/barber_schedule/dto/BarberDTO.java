package com.unifei.barber_schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private List<ServiceDTO> services;
}
