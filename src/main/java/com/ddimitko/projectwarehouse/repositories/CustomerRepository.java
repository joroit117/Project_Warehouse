package com.ddimitko.projectwarehouse.repositories;

import com.ddimitko.projectwarehouse.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByPIN(String pin);

}
