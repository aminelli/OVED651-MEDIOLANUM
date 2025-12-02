package it.example.customers.client;

import it.example.customers.model.Customer;
import it.example.customers.service.CustomersService;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class CustomersClient {
    public static void main(String[] args) throws Exception {
        // URL del WSDL esposto dal server
        URL wsdlURL = new URL("http://localhost:8080/customers?wsdl");

        // QName: deve combaciare con targetNamespace e serviceName del tuo servizio
        QName SERVICE_NAME = new QName("http://example.it/customers", "CustomersService");
        QName PORT_NAME    = new QName("http://example.it/customers", "CustomersServicePort");

        // Creazione del client dinamico
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        CustomersService customersApi = service.getPort(PORT_NAME, CustomersService.class);

        // 1. Inserimento di un nuovo Customer
        Customer c = new Customer(null, "Mario", "Rossi", "mario.rossi@example.it");
        Customer created = customersApi.insert(c);
        System.out.println("Creato Customer con ID: " + created.getId());

        // 2. Recupero per ID
        Customer found = customersApi.getById(created.getId());
        System.out.println("Recuperato: " + found.getFirstName() + " " + found.getLastName());

        // 3. Aggiornamento
        found.setEmail("m.rossi@example.it");
        Customer updated = customersApi.update(found);
        System.out.println("Aggiornato email: " + updated.getEmail());

        // 4. Ricerca
        List<Customer> results = customersApi.search("rossi");
        for (Customer cc : results) {
            System.out.println("Match: " + cc.getId() + " - " + cc.getEmail());
        }
    }
}
