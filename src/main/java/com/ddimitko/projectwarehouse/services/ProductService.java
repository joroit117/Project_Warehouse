package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Product;
import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> findAllByManufacturer(String manufacturer) {
        return productRepo.findAllByManufacturer(manufacturer);
    }

    public List<Product> findAllBySupplier(Supplier supplier) {
        return productRepo.findAllBySupplier(supplier);
    }

}
