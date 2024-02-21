package com.sandeepbegudem.customer.payments.service.service;
import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;
import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;
import com.sandeepbegudem.customer.payments.service.entity.Customer;
import com.sandeepbegudem.customer.payments.service.exception.CustomerNotFoundException;
import com.sandeepbegudem.customer.payments.service.mapper.CustomerMapper;
import com.sandeepbegudem.customer.payments.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper mapper;

    // save a customer
    public CustomerResponse saveCustomer(CustomerPaymentsRequest customerPaymentsRequest) {
        Customer customerList = mapper.dtoToEntity(customerPaymentsRequest);
        Customer savedCustomer = customerRepository.save(customerList);
        CustomerPaymentsRequest req = mapper.entityToDto(savedCustomer);
        CustomerResponse customerResponse = mapper.dtoToResponse(req);
        return customerResponse;
    }

    public CustomerResponse customerById(int id) throws CustomerNotFoundException {
        CustomerPaymentsRequest dto = null;
        try {
            Customer customer = customerRepository.findCustomerById(id);
            dto = mapper.entityToDto(customer);
            return mapper.dtoToResponse(dto);
        }
        catch (CustomerNotFoundException customerNotFoundException) {
            customerNotFoundException.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapper.dtoToResponse(dto);
    }

    // update customer
    public Customer updateCustomer(CustomerPaymentsRequest customerPaymentsRequest, Integer id) {
        Customer retrievedCustomer = customerRepository.findById(id).orElse(null);
        return customerRepository.save(retrievedCustomer);
    }

    // delete customer
    public void deleteCustomerById(int id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if ((customer != null ? customer.getId() : null) == null) {
            throw new RuntimeException("resource: not found" + id);
        }
        else
            customerRepository.deleteCustomerById(id);
    }

    // retrieve list of customers
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllCustomers()
                .stream()
                .map(CustomerMapper::apply)
                .collect(Collectors.toList());
    }
}
