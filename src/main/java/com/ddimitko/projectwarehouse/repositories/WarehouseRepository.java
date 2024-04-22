package com.ddimitko.projectwarehouse.repositories;

import com.ddimitko.projectwarehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
