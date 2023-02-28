package ru.stomprf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stomprf.db.DBConfig;
import ru.stomprf.main.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepo implements Dao<Integer, Customer>{

    private Connection connection;
    private Logger logger = LoggerFactory.getLogger(CustomerRepo.class);
    private final String SQL_SELECT_ALL = "SELECT * FROM customers";
    private final String SQL_SELECT_USER_ID = "SELECT * FROM customers WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM customers WHERE id = ?";
    private final String SQL_INSERT = "INSERT INTO customers(name, phone_number, discount) VALUES(?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE customers SET (name, phone_number, discount) = (?, ?, ?) WHERE id = ?";


    public CustomerRepo(@Autowired DBConfig dbConfig) throws SQLException {
        this.connection = dbConfig.dataSource().getConnection();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                int discount = rs.getInt("discount");
                customers.add(new Customer(id, name, phoneNumber, discount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findEntityById(Integer dbUserId) {
        Customer customer = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID);
            statement.setInt(1, dbUserId);
            ResultSet rs = statement.executeQuery();
            System.out.println("CHECKS");
            if (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                int discount = rs.getInt("discount");

                customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setPhoneNumber(phoneNumber);
                customer.setDiscount(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer delete(Integer id) {
        Customer customer = findEntityById(id);
        if (customer == null)
            return null;

        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE);)
        {
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            logger.info("Result of deleting user: " + result);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean delete(Customer customer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Customer save(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhoneNumber());
            statement.setInt(3, customer.getDiscount());

            int result = statement.executeUpdate();
            logger.info("Saved customer" + customer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        // Check if customers exists in database
        Customer dbCustomer = findEntityById(customer.getId());
        if (dbCustomer == null)
            return null;
        BeanUtils.copyProperties(customer, dbCustomer, "id");

        // Update customer
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);)
        {
            statement.setString(1, dbCustomer.getName());
            statement.setString(2, dbCustomer.getPhoneNumber());
            statement.setInt(3, dbCustomer.getDiscount());
            statement.setInt(4, dbCustomer.getId());

            int result = statement.executeUpdate();
            logger.info("Updated customer" + customer +"\n Result: "+  result);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return dbCustomer;
    }

}
