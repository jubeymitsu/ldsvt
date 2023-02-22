package ru.stomprf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.main.Customer;
import ru.stomprf.repo.CustomerRepo;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(@Autowired CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public List<Customer> customers(){
        return  customerRepo.findAll();
    }


}
