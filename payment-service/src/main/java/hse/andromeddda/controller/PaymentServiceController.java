package hse.andromeddda.controller;

/* PostMapping, GetMapping, RequestMapping, RestController, RequestBody, PathVariable */
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import hse.andromeddda.dto.BalanceResponse;
import hse.andromeddda.dto.CreateAccountRequest;
import hse.andromeddda.dto.DepositAccountRequest;

import hse.andromeddda.model.Account;
import hse.andromeddda.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentServiceController
{
    private final PaymentService paymentService;

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request)
    {
        return ResponseEntity.ok(paymentService.createAccount(request.userId()));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody DepositAccountRequest request)
    {
        return ResponseEntity.ok(paymentService.deposit(request.userId(), request.amount()));
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable Long userId)
    {
        return ResponseEntity.ok(paymentService.getBalance(userId));
    }
}