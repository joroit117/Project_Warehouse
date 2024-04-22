package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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
            }
        }
    }

    public void deleteSupplier(Supplier supplier){
        supplierRepo.delete(supplier);
    }

}
