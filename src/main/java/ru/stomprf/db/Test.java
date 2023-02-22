package ru.stomprf.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args){
        Connection connection = null;
        try {
            connection = new DBConfig().dataSource().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO customers(name, phone_number, discount) VALUES('Is', '+79216059950', 3)");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
