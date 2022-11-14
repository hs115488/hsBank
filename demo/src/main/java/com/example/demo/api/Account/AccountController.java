package com.example.demo.api.Account;

import com.example.demo.api.Transaction.Transaction;
import com.example.demo.api.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@ResponseBody
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
    public void deleteAccount(@RequestBody int accountNumber) {
            accountService.deleteAccount(accountNumber);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void withdraw(@RequestBody AccountTransaction accountTransaction) {
        accountService.withdraw(accountTransaction.getId(), accountTransaction.getAmount());
        transactionService.createTransaction(accountTransaction, "withdraw");
    }


}
