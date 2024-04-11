package com.ddimitko.projectwarehouse.repositories;

import com.ddimitko.projectwarehouse.models.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
}
