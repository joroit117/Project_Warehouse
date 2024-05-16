package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Product;
import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Log4j2
public class
ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> findAllByManufacturer(String manufacturer) {
        log.warn("Products for found!");
        return productRepo.findAllByManufacturer(manufacturer);
    }

    public List<Product> findAllBySupplier(Supplier supplier) {
        return productRepo.findAllBySupplier(supplier);
    }

    public List<Product> findAllByInWarehouseIsTrue(){
        return productRepo.findAllByInWarehouseIsTrue();
    }

}
