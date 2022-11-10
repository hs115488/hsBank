package com.example.demo.api.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody CreateAccount newAccount) {
        Account account = new Account();
        account.setFirstName(newAccount.firstName);
        account.setLastName(newAccount.lastName);
        account.setAccountType(newAccount.accountType);
        account.setBalance(0);
        account.setOverdraft(false);
        account.setAccountCreated(new Date());

        accountService.createAccount(account);

    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestBody int accountNumber) {
        accountService.deleteAccount(accountNumber);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deposit(@RequestBody AccountTransaction accountTransaction) {
        accountService.deposit(accountTransaction.id, accountTransaction.amount);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void withdraw(@RequestBody AccountTransaction accountTransaction) {
        accountService.withdraw(accountTransaction.id, accountTransaction.amount);
    }


}