package com.ddimitko.projectwarehouse.services;

import com.ddimitko.projectwarehouse.models.Customer;
import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.models.User;
import com.ddimitko.projectwarehouse.models.UserType;
import com.ddimitko.projectwarehouse.repositories.CustomerRepository;
import com.ddimitko.projectwarehouse.repositories.SupplierRepository;
import com.ddimitko.projectwarehouse.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
@Transactional
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserService() {
    }

    public List<User> findAll() {
        return this.userRepo.findAll();
    }


    public User findByUsernameAndPassword(String username, String password) {
        return this.userRepo.findByUsernameAndPassword(username, password);
    }

    public User findByUsername(String username) {
        return this.userRepo.findByUsername(username);

    }

    public Boolean authenticate(String username, String password) {
        User user = this.userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found.");
            return false;
        } else {
            if(password.equals(user.getPassword())) {
                log.warn("Authenticating...");
                return true;

            }
            else{
                log.warn("Wrong password.");
                return false;
            }
        }
    }

    public void addUser(String username, String password, String userType) {
        if (this.findByUsername(username) == null && !username.isEmpty() && !username.isBlank() && !password.isEmpty() && !password.isBlank()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUserType(UserType.valueOf(userType));
            this.userRepo.save(user);
            log.warn("User added.");
        }

    }

}
