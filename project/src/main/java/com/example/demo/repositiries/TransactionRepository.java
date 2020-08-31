package com.example.demo.repositiries;

import com.example.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Optional<Transaction> getByTransactionId(int id);
    List<Transaction> getTransactionsByDate(Date date);
}
