package com.asd.customerserviceapi.service.impl;

import com.asd.customerserviceapi.dto.request.CustomerRequestDto;
import com.asd.customerserviceapi.dto.response.AddressResponseDto;
import com.asd.customerserviceapi.dto.response.CustomerResponseDto;
import com.asd.customerserviceapi.entity.Customer;
import com.asd.customerserviceapi.repo.CustomerRepo;
import com.asd.customerserviceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    @Autowired
    private WebClient webClient;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void saveCustomer(CustomerRequestDto dto) {
        customerRepo.save(
                new Customer(dto.getId(), dto.getName(), dto.getAddressId(),
                         dto.getSalary())
        );
    }

    @Override
    public CustomerResponseDto findCustomer(String id) {
        var selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new RuntimeException("Not Found!");
        }
        var x=getAddressData(selectedCustomer.get().getAddressId());
        return new CustomerResponseDto(
                selectedCustomer.get().getId(),
                selectedCustomer.get().getName(),
                x,
                selectedCustomer.get().getSalary()
        );
    }

    @Override
    public void updateCustomer(CustomerRequestDto dto) {
        var selectedCustomer = customerRepo.findById(dto.getId());
        if (selectedCustomer.isEmpty()){
            throw new RuntimeException("Not Found!");
        }
        selectedCustomer.get().setName(dto.getName());
        selectedCustomer.get().setSalary(dto.getSalary());

        customerRepo.save(selectedCustomer.get());
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        List<CustomerResponseDto> list =new ArrayList<>();
        for (Customer c: customerRepo.findAll()
             ) {
            list.add(
                    new CustomerResponseDto(
                            c.getId(),c.getName(),getAddressData(c.getAddressId()),c.getSalary()
                    )
            );
        }
        return list;
    }

    private AddressResponseDto getAddressData(String addressId){
        webClient.get().uri("http://localhost:8003/api/v1/addresses/"+addressId)
                .retrieve().bodyToMono(AddressResponseDto.class).subscribe(data-> System.out.println(data.getCity()));
        return null;
    }

}
