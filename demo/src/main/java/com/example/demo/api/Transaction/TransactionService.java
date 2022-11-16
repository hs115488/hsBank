package com.example.demo.api.Transaction;

import com.example.demo.api.Account.Account;
import com.example.demo.api.Account.AccountRepository;
import com.example.demo.api.Account.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void createTransaction(AccountTransaction accountTransaction, String transactionType){

        Account account = accountRepository.findById(accountTransaction.getId()).orElse(null);
        Transaction transaction = new Transaction();

        transaction.setTransactionAmount(accountTransaction.getAmount());
        transaction.setNewBalance((int) account.getBalance());
        transaction.setAccountNumber(accountTransaction.getId());

        if(transactionType.equals("deposit"))
            transaction.setPreviousBalance(transaction.getNewBalance() - accountTransaction.getAmount());

        if(transactionType.equals("withdraw"))
            transaction.setPreviousBalance(transaction.getNewBalance() + accountTransaction.getAmount());

        transaction.setTransactionType(transactionType);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(int account_number){
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        return transactions
                .stream()
                .filter(t -> t.getAccountNumber() == account_number)
                .collect(Collectors.toList());
    }

}
