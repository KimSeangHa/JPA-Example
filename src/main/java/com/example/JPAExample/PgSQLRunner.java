package com.example.JPAExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PgSQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println("@@@@");
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE ACCOUNT(ID INTEGER NOT NULL, username VARCHAR(255), password VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

        // jdbcTemplate.execute("INSERT INTO ACCOUNT VALUES (3, 'kshman', '1234')");
    }
}
