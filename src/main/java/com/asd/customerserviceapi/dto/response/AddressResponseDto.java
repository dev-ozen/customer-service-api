package com.asd.customerserviceapi.dto.response;

import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponseDto {
    private String id;
    private String country;
    private String city;
}
