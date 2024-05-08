package com.miniproject.mnoutilityservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {
    @Id()
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(generator = "account_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "account_id")
    private long accountId;

    @Column(name = "balance")
    private long balance;

    @Column(name = "contact")
    private String contact;


    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
