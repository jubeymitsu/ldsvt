package ru.stomprf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stomprf.db.DataSourceTest;
import ru.stomprf.main.User;

import java.util.List;

@RestController
public class CustomerController {

    private final DataSourceTest dataSourceTest;

    public CustomerController(@Autowired DataSourceTest dataSourceTest) {
        this.dataSourceTest = dataSourceTest;
    }

    @GetMapping("/customers")
    public List<User> customers(){
        return dataSourceTest.testDataSource();
    }

    @GetMapping("/dasha")
    public String da(){
        return "<html>Любимому колдырю &#9825; &#9825; &#9825;</html>";
    }




}
