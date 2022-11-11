package com.example.demo.api.Transaction;

import com.example.demo.api.Account.Account;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Component
@Table
@Entity
public class Transaction {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int transaction_id;

    @NotNull
    @Column(name = "account_number")
    private int accountNumber;

    @NotNull
    @Column(name = "transaction_type")
    private String transactionType;

    @NotNull
    @Column(name = "transaction_amount")
    private long transactionAmount;

    @NotNull
    @Column(name = "previous_balance")
    private int previousBalance;

    @NotNull
    @Column(name = "new_balance")
    private int newBalance;

}
