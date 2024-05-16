package com.ddimitko.projectwarehouse.repositories;

import com.ddimitko.projectwarehouse.models.Product;
import com.ddimitko.projectwarehouse.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByManufacturer(String manufacturer);
    List<Product> findAllBySupplier(Supplier supplier);
    List<Product> findAllByInWarehouseIsTrue();

}
