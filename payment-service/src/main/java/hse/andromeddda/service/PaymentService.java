package hse.andromeddda.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import hse.andromeddda.dto.*;
import hse.andromeddda.model.*;
import hse.andromeddda.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService
{
    /* Databases */
    private final AccountRepository accountRepository;
    private final PaymentInboxRepository inboxRepository;
    private final PaymentOutboxRepository outboxRepository;

    /* for jsonning */
    private final ObjectMapper objectMapper;

    /*
        Create account request from API
    */
    @Transactional
    public Account createAccount(Long userId)
    {
        if (accountRepository.existsByUserId(userId)) {
            throw new IllegalStateException("Account for user " + userId + " already exists.");
        }
        return accountRepository.save(new Account(userId));
    }

    /*
        Deposit request from API

    */
    @Transactional
    public Account deposit(Long userId, BigDecimal amount)
    {
        /* cannot deposit negative */
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        /* get account from DB*/
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Account not found for user " + userId));

        /* update and save */
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    /*
        Balance response to API
    */
    public BalanceResponse getBalance(Long userId)
    {
        /* get account from DB */
        var balance = accountRepository
                .findByUserId(userId)
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalStateException("Account not found for user " + userId));

        /* create DTO and return */
        return new BalanceResponse(userId, balance);
    }


    /*
        Payment request from OrdersService (Kafka)
    */
    @Transactional
    @SneakyThrows
    public void processPayment(PaymentRequest request)
    {
        /* check if message already processed */
        if (inboxRepository.existsById(request.messageId())) return;

        /* save mesage id to Inbox */
        inboxRepository.save(new PaymentInboxMessage(request.messageId()));

        /* get account from DB */
        Account account = accountRepository.findByUserId(request.userId())
                .orElseThrow(() -> new IllegalStateException("Account not found for user " + request.userId()));

        /*
            BUSINESS LOGIC
        */
        String paymentStatus;
        if (account.getBalance().compareTo(request.amount()) >= 0)
        {
            /* subtract money from balance and update in DB */
            account.setBalance(account.getBalance().subtract(request.amount()));
            accountRepository.save(account);
            paymentStatus = "SUCCESS";
        }
        else {
            paymentStatus = "FAILURE";
        }

        /* Create payment outcome DTO for outbox */
        PaymentOutcome outcome
                = new PaymentOutcome(request.messageId(), request.orderId(), paymentStatus);

        /* DTO to json */
        String payload = objectMapper.writeValueAsString(outcome);

        /* Create outbox message*/
        PaymentOutboxMessage outboxMessage
                = new PaymentOutboxMessage("payment-outcome", String.valueOf(request.orderId()), payload);

        /* Save message to Outbox*/
        outboxRepository.save(outboxMessage);
    }
}