package zuzz.projects.e_commerce.microservices.customer_microservice.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zuzz.projects.e_commerce.microservices.customer_microservice.exceptions.CustomerNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String saveCustomer(CustomerRequest request) {
        if (request.id() == null) {
            return repository.save(mapper.toCustomer(request)).getId();
        }
        
        Customer customerFound = repository.findById(request.id())
            .orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", request.id())
            ));
        customerFound = mapper.toCustomer(request);
        return repository.save(customerFound).getId();
    }

    public CustomerResponse getCustomerById(String customerId) {
        return repository.findById(customerId)
            .map(mapper::toCustomerResponse)
            .orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", customerId)
            ));
    }

    public List<CustomerResponse> getCustomers() {
        return repository.findAll().stream()
            .map(mapper::toCustomerResponse)
            .toList();
    }

    public void deleteCustomerById(String customerId) {
        repository
            .findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", customerId)
            ));
        repository.deleteById(customerId);
    }
}
