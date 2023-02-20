package ru.stomprf.repo;

import org.springframework.stereotype.Component;
import ru.stomprf.main.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepo {

    public List<User> getDbUsers() {
        return dbUsers;
    }

    public void setDbUsers(List<User> dbUsers) {
        this.dbUsers = dbUsers;
    }

    private List<User> dbUsers = new ArrayList<>();

    public void initialize(){

    }

}
