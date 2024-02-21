package com.sandeepbegudem.customer.payments.service.controller;

import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.exception.CustomerNotFoundException;
import com.sandeepbegudem.customer.payments.service.service.CustomerService;
import com.sandeepbegudem.customer.payments.service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public CustomerController(CustomerService customerService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CustomerResponse> insertCustomer(@RequestBody CustomerPaymentsRequest request){
        return new ResponseEntity<>(customerService.saveCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CustomerResponse> retrieveCustomerById(@PathVariable Integer id) throws CustomerNotFoundException{
        return new ResponseEntity<>(customerService.customerById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCustomerById(@PathVariable Integer id) throws CustomerNotFoundException {
        if (null != id) customerService.deleteCustomerById(id);
        else
            throw new RuntimeException("id : " + id + " can't be null");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
}
