package com.asd.customerserviceapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {
    private String id;
    private String name;
    private String addressId;
    private double salary;
}
