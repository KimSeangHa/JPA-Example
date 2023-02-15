package com.example.JPAExample.account;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        Account account = new Account();

        account.setUsername("kshman");
        account.setPassword("1234");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull();

        Optional<Account> exisitingAccount = accountRepository.findByUsername(account.getUsername());
        assertThat(exisitingAccount).isNotEmpty();

        Optional<Account> notExisitingAccount = accountRepository.findByUsername("kshman");
        assertThat(notExisitingAccount).isEmpty();
    }

}