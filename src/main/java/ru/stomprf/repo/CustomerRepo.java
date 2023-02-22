package ru.stomprf.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stomprf.db.DBInteraction;
import ru.stomprf.main.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CustomerRepo {

    final
    DBInteraction dbInteraction;

    public CustomerRepo(@Autowired DBInteraction dbInteraction) {
        this.dbInteraction = dbInteraction;
    }

    public List<Customer> findAll() {
        //Complete shit
        return dbInteraction.selectAllMethod();
    }

    public List<Customer> findByPhoneNumber(String phoneNumber){
        return Collections.emptyList();
    }

    public int delete(Long id){
        return 0;
    }

    public Customer save(Customer customer){
        return new Customer();
    }

}
