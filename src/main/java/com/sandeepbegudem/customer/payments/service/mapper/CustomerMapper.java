package com.sandeepbegudem.customer.payments.service.mapper;import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;import com.sandeepbegudem.customer.payments.service.entity.Customer;import lombok.*;import org.springframework.stereotype.Component;@AllArgsConstructor@Setter@Getter@ToString@Componentpublic class CustomerMapper {    public CustomerPaymentsRequest entityToDto(Customer customer){        CustomerPaymentsRequest dto = new CustomerPaymentsRequest();        dto.setId(customer.getId());        dto.setFirstname(customer.getFirstname());        dto.setLastname(customer.getLastname());        dto.setAge(customer.getAge());        dto.setAddress(customer.getAddress());        dto.setCity(customer.getCity());        dto.setState(customer.getState());        dto.setPayments(customer.getPayments());        return dto;    }    public Customer dtoToEntity(CustomerPaymentsRequest customerPaymentsRequest){        Customer customer = new Customer();        customer.setId(customerPaymentsRequest.getId());        customer.setFirstname(customerPaymentsRequest.getFirstname());        customer.setLastname(customerPaymentsRequest.getLastname());        customer.setAge(customerPaymentsRequest.getAge());        customer.setAddress(customerPaymentsRequest.getAddress());        customer.setCity(customerPaymentsRequest.getCity());        customer.setState(customerPaymentsRequest.getState());        customer.setPayments(customerPaymentsRequest.getPayments());        return customer;    }}