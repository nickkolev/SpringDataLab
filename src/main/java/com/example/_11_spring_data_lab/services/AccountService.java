package com.example._11_spring_data_lab.services;

import com.example._11_spring_data_lab.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal money, int id);
    void transferMoney(BigDecimal money, int id);

    void createAccount(Account account);
}
