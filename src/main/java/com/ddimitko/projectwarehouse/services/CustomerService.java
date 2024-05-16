package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Customer;
import com.ddimitko.projectwarehouse.repositories.CustomerRepository;
import com.ddimitko.projectwarehouse.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Log4j2
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> findAllCustomers() {
        return this.customerRepo.findAll();
    }

    public Customer findByPIN(String PIN){
        return this.customerRepo.findByPIN(PIN);
    }

    public void addCustomer(String firstName, String lastName, String PIN){
        if(!firstName.isEmpty() && !firstName.isBlank() &&
                !lastName.isEmpty() && !lastName.isBlank()) {
            if(customerRepo.findByPIN(PIN) == null){
                Customer customer = new Customer();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setPIN(PIN);
                this.customerRepo.save(customer);
                log.warn("Customer added!");
            }
        }
    }

    public void deleteCustomer(Customer customer){
        customerRepo.delete(customer);
        log.warn("Customer deleted!");
    }

}
