package com.example.demo.api.Account;

import com.example.demo.api.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;


@Controller

public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;




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
    public void deleteAccount(@RequestBody int accountNumber) { accountService.deleteAccount(accountNumber);}






}
