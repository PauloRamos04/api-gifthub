package com.example.GiftHub.service;

import com.example.GiftHub.domain.payment.PaymentStatus;
import com.example.GiftHub.domain.payment.PaymentTransaction;
import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.repository.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransaction initiatePayment(User user, BigDecimal amount) {
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setUser(user);
        paymentTransaction.setAmount(amount);
        paymentTransaction.setStatus(PaymentStatus.PENDING);
        paymentTransaction.setCreatedAt(LocalDateTime.now());
        return paymentTransactionRepository.save(paymentTransaction);
    }

    public PaymentTransaction simulatePayment(User user, BigDecimal amount) {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setStatus(PaymentStatus.SUCCESS); // Supondo que o pagamento sempre tem sucesso
        transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }

    public PaymentTransaction savePaymentTransaction(PaymentTransaction transaction) {
        return paymentTransactionRepository.save(transaction);
    }
}
