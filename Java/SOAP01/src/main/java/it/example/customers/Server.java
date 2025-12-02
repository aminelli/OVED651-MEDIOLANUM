package it.example.customers;

import it.example.customers.service.CustomersServiceImpl;

// import javax.xml.ws.Endpoint;
import jakarta.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:8080/customers";
        Endpoint.publish(url, new CustomersServiceImpl());
        System.out.println("SOAP CustomersService pubblicato su: " + url + "?wsdl");
        System.out.println("Premi Ctrl+C per fermare.");
    }
}
