package com.example._11_spring_data_lab.services;

import com.example._11_spring_data_lab.models.Account;
import com.example._11_spring_data_lab.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal money, int id) {

        Account account = accountRepository.getById(id);

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.subtract(money);

        if(newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot withdraw more money than you have");
        }

        account.setBalance(newBalance);
    }

    @Override
    public void transferMoney(BigDecimal money, int id) {
        Account account = accountRepository.getById(id);

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(money);

        if (money.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Cannot deposit negative amount of money");
        }

        account.setBalance(newBalance);
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.save(account);
    }
}
