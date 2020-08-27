package com.example.demo.repositiries;

import com.example.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction getByTransactionId(int transactionId);
    List<Transaction> getByDate(String date);
}
