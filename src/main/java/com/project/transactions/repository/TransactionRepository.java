package com.project.transactions.repository;

import com.project.transactions.domain.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllByAccountId(Long accountId);
}
