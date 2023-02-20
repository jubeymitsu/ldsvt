package ru.stomprf.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stomprf.main.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSourceTest {

    public DataSourceTest(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final DataSource dataSource;

    public List<User> testDataSource() {
        List<User> dbUsers = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from customers");
        ) {
            while(rs.next()){
                Long id = rs.getLong(" id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                dbUsers.add(new User(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbUsers;
    }

}
