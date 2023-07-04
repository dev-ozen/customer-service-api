package com.asd.customerserviceapi.service;


import com.asd.customerserviceapi.dto.request.CustomerRequestDto;
import com.asd.customerserviceapi.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerRequestDto dto);
    public CustomerResponseDto findCustomer(String id);
    public void updateCustomer(CustomerRequestDto dto);
    public void deleteCustomer(String id);
    public List<CustomerResponseDto> findAllCustomers();
}
