package ru.stomprf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.main.Customer;
import ru.stomprf.repo.CustomerRepo;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepo customerRepo;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(@Autowired CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public List<Customer> customers(){
        return  customerRepo.findAll();
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        logger.info("Inserted customer is: " + customer);
        return customerRepo.save(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerRepo.delete(id);
    }


}
