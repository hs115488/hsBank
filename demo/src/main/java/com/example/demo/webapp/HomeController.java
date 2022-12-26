package com.example.demo.webapp;

import com.example.demo.api.Account.AccountService;
import com.example.demo.api.Account.AccountTransaction;
import com.example.demo.api.Transaction.Transaction;
import com.example.demo.api.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/depositForm")
    public String depositForm(){
        return "depositForm";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String deposit(@RequestParam String id, @RequestParam String amount) throws Exception {
        accountService.transaction(Integer.valueOf(id), Integer.valueOf(amount), "deposit");
        AccountTransaction accountTransaction = new AccountTransaction(id, amount);

        transactionService.createTransaction(accountTransaction, "deposit");

        return "confirmTransaction";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(@RequestParam String id, @RequestParam String amount) throws Exception {
        accountService.transaction(Integer.valueOf(id), Integer.valueOf(amount), "withdraw");
        AccountTransaction accountTransaction = new AccountTransaction(id, amount);

        transactionService.createTransaction(accountTransaction, "withdraw");

        return "confirmTransaction";
    }



    @RequestMapping("/withdrawForm")
    public String withdrawForm(){ return "withdrawForm"; }

    @PostMapping("/getTransactions")
    public String transactionsList(Model model, @RequestParam String accountNumber){
        List<Transaction> transactions =((List<Transaction>) transactionService.getTransactionsByUser(Integer.valueOf(String.valueOf(accountNumber))));
        model.addAttribute("transactions", transactions);

        return "transactionsList";
    }




}
