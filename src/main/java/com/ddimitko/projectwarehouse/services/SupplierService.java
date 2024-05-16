package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Log4j2
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepo;

    public List<Supplier> findAllSuppliers() {
        return this.supplierRepo.findAll();
    }

    public Supplier findByName(String name){
        return this.supplierRepo.findByName(name);
    }

    public void addSupplier(String supplierName) {
        if(!supplierName.isEmpty() && !supplierName.isBlank()) {
            if (supplierRepo.findByName(supplierName) == null) {
                Supplier supplier = new Supplier();
                supplier.setName(supplierName);
                this.supplierRepo.save(supplier);
                log.warn("Supplier added!");
            }
        }
    }

    public void deleteSupplier(Supplier supplier){
        log.warn("Supplier deleted!");
        supplierRepo.delete(supplier);
    }

}
