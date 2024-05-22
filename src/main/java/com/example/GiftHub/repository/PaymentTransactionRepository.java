package com.example.GiftHub.repository;

import com.example.GiftHub.domain.payment.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    // Métodos adicionais, se necessário
}
