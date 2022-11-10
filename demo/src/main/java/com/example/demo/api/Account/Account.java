package com.example.demo.api.Account;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Component

@Table
@Entity
public class Account {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_number")
    private Integer id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "account_type")
    private String accountType;

    @NotNull
    @Column(name = "balance")
    private long balance;

    @NotNull
    @Column(name = "overdraft_status")
    private boolean overdraft;

    @NotNull
    @Column(name = "account_created")
    private Date accountCreated;



}
