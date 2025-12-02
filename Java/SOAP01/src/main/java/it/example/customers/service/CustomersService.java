package it.example.customers.service;

import it.example.customers.model.Customer;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import java.util.List;

@WebService(
        name = "CustomersService",
        targetNamespace = "http://example.it/customers"
)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface CustomersService {

    @WebMethod(operationName = "Add")
    @WebResult(name = "Customer")
    Customer add(
            @WebParam(name = "Customer") Customer customer
    );

    @WebMethod(operationName = "Insert")
    @WebResult(name = "Customer")
    Customer insert(
            @WebParam(name = "Customer") Customer customer
    );

    @WebMethod(operationName = "Update")
    @WebResult(name = "Customer")
    Customer update(
            @WebParam(name = "Customer") Customer customer
    );

    @WebMethod(operationName = "GetById")
    @WebResult(name = "Customer")
    Customer getById(
            @WebParam(name = "Id") long id
    );

    @WebMethod(operationName = "Search")
    @WebResult(name = "Customers")
    List<Customer> search(
            @WebParam(name = "Query") String query
    );
}
