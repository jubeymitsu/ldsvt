package ru.stomprf.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stomprf.main.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInteraction {

    private final DBConfig dbConfig;
    private Connection connection;
    private final String SQL_SELECT = "select * from customers";

    public DBInteraction(@Autowired DBConfig dbConfig) throws SQLException {
        this.dbConfig = dbConfig;
        this.connection = dbConfig.dataSource().getConnection();
    }

    public List<Customer> selectAllMethod() {
        //Complete shit
        List<Customer> customers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT)) {
            while (rs.next()) {
                Long id = (long) rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                int discount = rs.getInt("discount");
                customers.add(new Customer(id, name, phoneNumber, discount));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

}
