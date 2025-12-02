package it.example.customers.repo;

import it.example.customers.model.Customer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CustomerRepository {
    private final Map<Long, Customer> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Customer insert(Customer c) {
        long id = seq.getAndIncrement();
        c.setId(id);
        store.put(id, clone(c));
        return clone(c);
    }

    public Customer add(Customer c) {
        // alias di insert: se ha id nullo -> insert; se ha id presente e libero -> inserisce
        if (c.getId() == null) return insert(c);
        if (store.containsKey(c.getId())) throw new IllegalArgumentException("ID already exists: " + c.getId());
        store.put(c.getId(), clone(c));
        return clone(c);
    }

    public Customer update(Customer c) {
        if (c.getId() == null || !store.containsKey(c.getId()))
            throw new NoSuchElementException("Customer not found: " + c.getId());
        store.put(c.getId(), clone(c));
        return clone(c);
    }

    public Optional<Customer> getById(long id) {
        Customer c = store.get(id);
        return Optional.ofNullable(c == null ? null : clone(c));
    }

    public List<Customer> search(String query) {
        if (query == null || query.isBlank()) return all();
        String q = query.toLowerCase(Locale.ROOT);
        return store.values().stream()
                .filter(c ->
                        (c.getFirstName() != null && c.getFirstName().toLowerCase(Locale.ROOT).contains(q)) ||
                        (c.getLastName() != null && c.getLastName().toLowerCase(Locale.ROOT).contains(q)) ||
                        (c.getEmail() != null && c.getEmail().toLowerCase(Locale.ROOT).contains(q)))
                .map(this::clone)
                .collect(Collectors.toList());
    }

    public List<Customer> all() {
        return store.values().stream().map(this::clone).collect(Collectors.toList());
    }

    private Customer clone(Customer c) {
        return new Customer(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail());
    }
}
