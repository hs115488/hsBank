package com.example.demo.api.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/getTransactionsByUser/{account_number}", method = RequestMethod.GET)
    public List<Transaction> getTransactionsByUser(@PathVariable String account_number){
        return transactionService.getTransactionsByUser(Integer.parseInt(account_number));
    }
}
