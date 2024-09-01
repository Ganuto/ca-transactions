package com.project.transactions.service;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;
import com.project.transactions.domain.exception.BusinessException;
import com.project.transactions.mock.TransactionMock;
import com.project.transactions.repository.TransactionRepository;
import com.project.transactions.service.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionRepository transactionRepository;

    @SpyBean
    private TransactionServiceImpl transactionService;

    @Test
    public void createPurchaseTransactionSuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(-10.23);
        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.PURCHASE, amount);
        Transaction transaction = TransactionMock.createTransaction(OperationType.PURCHASE, amount);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponse transactionResponse = transactionService.execute(transactionRequest);

        verify(accountService).findById(anyLong());
        assertEquals(transaction.getId(), transactionResponse.getTransactionId());
        assertEquals(transaction.getAccountId(), transactionResponse.getAccountId());
        assertEquals(transaction.getOperationType().getId(), transactionResponse.getOperationTypeId());
        assertEquals(transaction.getAmount(), transactionResponse.getAmount());
    }

    @Test
    public void createPurchaseTransactionUnsuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(20.23);

        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.PURCHASE, amount);

        BusinessException businessException = assertThrows(BusinessException.class, () -> transactionService.execute(transactionRequest));

        assertEquals(String.format("The operation [%s] with id [%s] cannot have positive amount [%s].", OperationType.PURCHASE, OperationType.PURCHASE.getId(), amount), businessException.getMessage());
    }

    @Test
    public void createInstallmentPurchaseTransactionSuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(-11.23);
        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.INSTALLMENT_PURCHASE, amount);
        Transaction transaction = TransactionMock.createTransaction(OperationType.INSTALLMENT_PURCHASE, amount);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponse transactionResponse = transactionService.execute(transactionRequest);

        verify(accountService).findById(anyLong());
        assertEquals(transaction.getId(), transactionResponse.getTransactionId());
        assertEquals(transaction.getAccountId(), transactionResponse.getAccountId());
        assertEquals(transaction.getOperationType().getId(), transactionResponse.getOperationTypeId());
        assertEquals(transaction.getAmount(), transactionResponse.getAmount());
    }

    @Test
    public void createInstallmentPurchaseTransactionUnsuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(10.23);

        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.INSTALLMENT_PURCHASE, amount);

        BusinessException businessException = assertThrows(BusinessException.class, () -> transactionService.execute(transactionRequest));

        assertEquals(String.format("The operation [%s] with id [%s] cannot have positive amount [%s].", OperationType.INSTALLMENT_PURCHASE, OperationType.INSTALLMENT_PURCHASE.getId(), amount), businessException.getMessage());
    }

    @Test
    public void createWithdrawTransactionSuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(-11.23);
        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.WITHDRAWAL, amount);
        Transaction transaction = TransactionMock.createTransaction(OperationType.WITHDRAWAL, amount);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponse transactionResponse = transactionService.execute(transactionRequest);

        verify(accountService).findById(anyLong());
        assertEquals(transaction.getId(), transactionResponse.getTransactionId());
        assertEquals(transaction.getAccountId(), transactionResponse.getAccountId());
        assertEquals(transaction.getOperationType().getId(), transactionResponse.getOperationTypeId());
        assertEquals(transaction.getAmount(), transactionResponse.getAmount());
    }

    @Test
    public void createWithdrawTransactionUnsuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(10.23);

        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.WITHDRAWAL, amount);

        BusinessException businessException = assertThrows(BusinessException.class, () -> transactionService.execute(transactionRequest));

        assertEquals(String.format("The operation [%s] with id [%s] cannot have positive amount [%s].", OperationType.WITHDRAWAL, OperationType.WITHDRAWAL.getId(), amount), businessException.getMessage());
    }

    @Test
    public void createPaymentTransactionSuccessfully() {
        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.PAYMENT, BigDecimal.valueOf(10.23));
        Transaction transaction = TransactionMock.createTransaction(OperationType.PAYMENT, BigDecimal.valueOf(10.23));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponse transactionResponse = transactionService.execute(transactionRequest);

        verify(accountService).findById(anyLong());
        assertEquals(transaction.getId(), transactionResponse.getTransactionId());
        assertEquals(transaction.getAccountId(), transactionResponse.getAccountId());
        assertEquals(transaction.getOperationType().getId(), transactionResponse.getOperationTypeId());
        assertEquals(transaction.getAmount(), transactionResponse.getAmount());
    }

    @Test
    public void createPaymentTransactionUnsuccessfully() {
        BigDecimal amount = BigDecimal.valueOf(-10.23);

        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.PAYMENT, amount);

        BusinessException businessException = assertThrows(BusinessException.class, () -> transactionService.execute(transactionRequest));

        assertEquals(String.format("The operation [%s] with id [%s] cannot have negative amount [%s].", OperationType.PAYMENT, OperationType.PAYMENT.getId(), amount), businessException.getMessage());
    }

    @Test
    public void createTransactionWithNonexistentAccountId() {
        BigDecimal amount = BigDecimal.valueOf(10.23);

        TransactionRequest transactionRequest = TransactionMock.createTransactionRequest(OperationType.PAYMENT, amount);

        when(accountService.findById(anyLong())).thenThrow(BusinessException.class);
        assertThrows(BusinessException.class, () -> transactionService.execute(transactionRequest));

        verify(accountService).findById(anyLong());
    }
}
