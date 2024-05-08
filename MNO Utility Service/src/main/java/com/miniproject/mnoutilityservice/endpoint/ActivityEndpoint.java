package com.miniproject.mnoutilityservice.endpoint;


import java.util.ArrayList;
import java.util.List;

import api.mnoutilityservice.miniproject.com.activities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.miniproject.mnoutilityservice.entity.Transaction;
import com.miniproject.mnoutilityservice.service.AccountService;
import com.miniproject.mnoutilityservice.service.TransactionService;


@RequiredArgsConstructor
@Endpoint
public class ActivityEndpoint {
    private static final String NAMESPACE_URI = "http://com.miniproject.mnoutilityservice.api/activities";


    final private AccountService accountService;
    final private TransactionService transactionService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "balanceCheckingRequest")
    @ResponsePayload
    public BalanceCheckingResponse getAccountBalance(@RequestPayload BalanceCheckingRequest request) {
        BalanceCheckingResponse response = new BalanceCheckingResponse();

        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountService.readAccount(request.getUserId()), accountInfo);
        response.setAccountInfo(accountInfo);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "purchaseAirtimeRequest")
    @ResponsePayload
    public PurchaseAirtimeResponse purchaseAirtime(@RequestPayload PurchaseAirtimeRequest request) throws Exception {
        PurchaseAirtimeResponse response = new PurchaseAirtimeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        transactionService.purchaseAirTime(request.getUserId(), request.getAmount());
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("Transaction successful");
        response.setServiceStatus(serviceStatus);

        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "airtimeTransferRequest")
    @ResponsePayload
    public AirtimeTransferResponse airtimeTransfer(@RequestPayload AirtimeTransferRequest request) {
            AirtimeTransferResponse response = new AirtimeTransferResponse();
            ServiceStatus serviceStatus = new ServiceStatus();

            transactionService.bulkAirtimePurchase(request.getUserId(), request.getAmount(), List.of(request.getContact()));
            serviceStatus.setStatus("Success");
            serviceStatus.setMessage("Airtime successful transferred");
            response.setServiceStatus(serviceStatus);

            return response;


    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bulkAirtimePurchaseRequest")
    @ResponsePayload
    public BulkAirtimePurchaseResponse bulkAirtimeTransfer(@RequestPayload BulkAirtimePurchaseRequest request) {
        BulkAirtimePurchaseResponse response = new BulkAirtimePurchaseResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        transactionService.bulkAirtimePurchase(request.getUserId(), request.getAmount(), request.getContacts());
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("Bulk airtime purchase successful");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "transactionHistoryRequest")
    @ResponsePayload
    public TransactionHistoryResponse getTransactionHistory(@RequestPayload TransactionHistoryRequest request) throws Exception {
        TransactionHistoryResponse response = new TransactionHistoryResponse();
        List<TransactionInfo> transactionInfoList = new ArrayList<>();
        List<Transaction> transactions = transactionService.getTransactions(request.getUserId());
        transactions.forEach(transaction -> {
            TransactionInfo transactionInfo = new TransactionInfo();
            BeanUtils.copyProperties(transaction, transactionInfo);
            transactionInfoList.add(transactionInfo);
        });
        response.setTransactionHistory(transactionInfoList);
        return response;
    }
}
