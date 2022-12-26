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

    public void transaction(int id, long amount, String transactionType) throws Exception {
        Account account = accountRepository.findById(Integer.valueOf(id)).orElse(null);

        switch(transactionType){
            case "deposit":
                if(amount < 0){throw new Exception("Deposit amount cannot be less than zero");}
                else{account.setBalance(account.getBalance() + amount);}
                accountRepository.save(account);
                break;

            case "withdraw":
                if(amount < 0){throw new Exception("Withdrawal amount cannot be less than zero");}
                else{account.setBalance(account.getBalance() - amount);}
                accountRepository.save(account);
                break;
            }
        }
}
