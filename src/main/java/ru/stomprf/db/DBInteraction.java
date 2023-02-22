package ru.stomprf.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final String SQL_SELECT = "SELECT * FROM customers";
    private final String SQL_INSERT = "INSERT INTO customers(name, phone_number, discount) VALUES(?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM customers WHERE id = ?";
    private Logger logger = LoggerFactory.getLogger(DBInteraction.class);

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

    public Customer insertRowMethod(Customer customer){
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT);)
              {
                  String name = customer.getName();
                  String phone_number = customer.getPhoneNumber();
                  int dicsount = customer.getDiscount();

                  statement.setString(1, name);
                  statement.setString(2, phone_number);
                  statement.setInt(3, dicsount);
                  int result = statement.executeUpdate();
                  logger.info("Result of inserting new user: " + result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return customer;
    }

    public Customer deleteRowMethod(Long id){
        List<Customer> customers = selectAllMethod();
        Customer customer = customers.stream()
                .filter(user -> user.getId().equals(id)).
                findAny().
                orElse(null);

        if (customer != null){
            logger.info("Users found: " + customer);
            try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE);)
            {
                statement.setLong(1, id);
                int result = statement.executeUpdate();
                logger.info("Result of deleting user: " + result);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        logger.warn("Customers didn't find: " + customer);
        return customer;
    }
}
