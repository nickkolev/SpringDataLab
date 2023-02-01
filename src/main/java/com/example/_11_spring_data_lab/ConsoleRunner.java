package com.example._11_spring_data_lab;

import com.example._11_spring_data_lab.models.Account;
import com.example._11_spring_data_lab.models.User;
import com.example._11_spring_data_lab.repositories.AccountRepository;
import com.example._11_spring_data_lab.services.AccountService;
import com.example._11_spring_data_lab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Niki", 22);

        Account account = new Account(new BigDecimal("25000"));
        account.setUser(user);

        user.setAccounts(new HashSet<>() {{
            add(account);
        }});

        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal("20000"), account.getId());
        accountService.transferMoney(new BigDecimal("30000"), account.getId());
    }
}
