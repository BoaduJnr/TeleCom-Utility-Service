package com.miniproject.mnoutilityservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id()
    @SequenceGenerator(allocationSize = 1,name = "transaction_seq", sequenceName = "transaction_seq")
    @GeneratedValue(generator = "transaction_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "balance")
    private long balance;

    @Column(name = "received")
    private long received;

    @Column(name = "transferred")
    private long transferred;

    @Column(name = "recipient_id")
    private long recipientId;

    @Column(name = "contact")
    private String contact;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
