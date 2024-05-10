package com.example.GiftHub.service;

import com.example.GiftHub.domain.customer.Customer;
import com.example.GiftHub.domain.customer.CustomerDTO;
import com.example.GiftHub.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = this.customerRepository.findAll();

        if (customers.isEmpty()){
            throw new Exception("Não há clientes cadastrados");
        }

        return customers;
    }

    public Customer getCustomerById(Long id) throws Exception {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado"));
    }
    public Customer createCustomer(CustomerDTO customer) throws Exception{
        if (customer == null){
            throw new Exception("Erro ao cadastrar o cliente, faltando informações necessarias");
        }
        Customer newCustomer = new Customer(customer);
        this.customerRepository.save(newCustomer);
        return newCustomer;
    }

    public void deleteCustomer(Long id) throws Exception {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new Exception("Cliente não encontrado para exclusão");
        }
    }
}
