package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Warehouse;
import com.ddimitko.projectwarehouse.repositories.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepo;

    public Warehouse initializeAtStartup(){

        Warehouse warehouse;

        if(warehouseRepo.count() == 0) {
            warehouse = new Warehouse();
            warehouse.setCashRegister(10000.00);
            warehouseRepo.save(warehouse);
        }
        else{
            warehouse = warehouseRepo.findById(1L).get();
        }

        return warehouse;
    }

    public Warehouse findById(Long id){
        return warehouseRepo.findById(1L).get();
    }

}
