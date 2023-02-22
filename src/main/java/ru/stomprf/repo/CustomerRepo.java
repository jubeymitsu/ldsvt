package ru.stomprf.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stomprf.db.DBInteraction;
import ru.stomprf.main.Customer;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerRepo {

    final DBInteraction dbInteraction;

    public CustomerRepo(@Autowired DBInteraction dbInteraction) {
        this.dbInteraction = dbInteraction;
    }

    public List<Customer> findAll() {
        //Complete shit
        return dbInteraction.selectAllMethod();
    }

    public Customer save(Customer newCustomer){
        if (newCustomer.getPhoneNumber().length() < 12)
            return null;
        Customer customer = dbInteraction.insertRowMethod(newCustomer);
        if (customer == null)
            return null;
        return customer;
    }

    public List<Customer> findByPhoneNumber(String phoneNumber){
        return Collections.emptyList();
    }

    public Customer delete(Long id){
       return dbInteraction.deleteRowMethod(id);
    }


}
