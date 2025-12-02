package it.example.customers.service;

import it.example.customers.model.Customer;
import it.example.customers.repo.CustomerRepository;

import jakarta.jws.WebService;
import java.util.NoSuchElementException;

@WebService(
        endpointInterface = "it.example.customers.service.CustomersService",
        serviceName = "CustomersService",
        portName = "CustomersServicePort",
        targetNamespace = "http://example.it/customers"
)
public class CustomersServiceImpl implements CustomersService {

    private final CustomerRepository repo = new CustomerRepository();

    @Override
    public Customer add(Customer customer) {
        validateCustomerForCreate(customer);
        return repo.add(customer);
    }

    @Override
    public Customer insert(Customer customer) {
        validateCustomerForCreate(customer);
        return repo.insert(customer);
    }

    @Override
    public Customer update(Customer customer) {
        validateCustomerForUpdate(customer);
        return repo.update(customer);
    }

    @Override
    public Customer getById(long id) {
        return repo.getById(id).orElseThrow(
                () -> new NoSuchElementException("Customer not found: " + id)
        );
    }

    @Override
    public java.util.List<Customer> search(String query) {
        return repo.search(query);
    }

    private void validateCustomerForCreate(Customer c) {
        if (c == null) throw new IllegalArgumentException("Customer is required");
        if (isBlank(c.getFirstName()) || isBlank(c.getLastName()))
            throw new IllegalArgumentException("FirstName and LastName are required");
        if (isBlank(c.getEmail())) throw new IllegalArgumentException("Email is required");
    }

    private void validateCustomerForUpdate(Customer c) {
        if (c == null) throw new IllegalArgumentException("Customer is required");
        if (c.getId() == null) throw new IllegalArgumentException("Id is required for update");
        if (isBlank(c.getFirstName()) || isBlank(c.getLastName()))
            throw new IllegalArgumentException("FirstName and LastName are required");
        if (isBlank(c.getEmail())) throw new IllegalArgumentException("Email is required");
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
