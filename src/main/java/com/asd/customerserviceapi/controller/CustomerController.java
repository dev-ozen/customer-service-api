package com.asd.customerserviceapi.controller;

import com.asd.customerserviceapi.dto.request.CustomerRequestDto;
import com.asd.customerserviceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asd.customerserviceapi.util.StandardResponseEntity;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<StandardResponseEntity> saveCustomer(@RequestBody CustomerRequestDto dto){
        customerService.saveCustomer(dto);
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"Created "+dto.getName(),null),
                HttpStatus.CREATED
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponseEntity> findCustomer(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"data for "+id,customerService.findCustomer(id)),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponseEntity> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponseEntity(204,"data deleted "+id,null),
                HttpStatus.NO_CONTENT
        );
    }

    @PutMapping
    public ResponseEntity<StandardResponseEntity> updateCustomer(@RequestBody CustomerRequestDto dto){
        customerService.updateCustomer(dto);
        return new ResponseEntity<>(
                new StandardResponseEntity(201,"data updated "+dto.getName(),null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponseEntity> findAllCustomers(){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"all data",customerService.findAllCustomers()),
                HttpStatus.OK
        );
    }
}
