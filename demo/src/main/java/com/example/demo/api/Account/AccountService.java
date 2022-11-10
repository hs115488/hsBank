package com.example.demo.api.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public int overdraftFee = 35;
    @Autowired
    private AccountRepository accountRepository;

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteById(id);
    }

    public void deposit(int id, long amount){
        Account account = accountRepository.findById(Integer.valueOf(id)).orElse(null);
        account.setBalance(account.getBalance() + amount);

        if(account.getBalance() < 0){
            account.setOverdraft(true);
            account.setBalance(account.getBalance() - overdraftFee);
        }
        accountRepository.save(account);

    }
    public void withdraw(int id, long amount){
        Account account = accountRepository.findById(Integer.valueOf(id)).orElse(null);
        account.setBalance(account.getBalance() - amount);

        if(account.getBalance() < 0){
            account.setOverdraft(true);
            account.setBalance(account.getBalance() - overdraftFee);
        }

        accountRepository.save(account);
    }

}
