package com.project.transactions.domain;

import com.project.transactions.domain.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class OperationTypeTest {

    @Test
    public void getPurchaseByIdSuccessfully() {
        int operationTypeId = OperationType.PURCHASE.getId();

        OperationType operationType = OperationType.getById(operationTypeId);

        assertEquals(operationTypeId, operationType.getId());
    }

    @Test
    public void getInstallmentPurchaseByIdSuccessfully() {
        int operationTypeId = OperationType.INSTALLMENT_PURCHASE.getId();

        OperationType operationType = OperationType.getById(operationTypeId);

        assertEquals(operationTypeId, operationType.getId());
    }

    @Test
    public void getWithdrawByIdSuccessfully() {
        int operationTypeId = OperationType.WITHDRAWAL.getId();

        OperationType operationType = OperationType.getById(operationTypeId);

        assertEquals(operationTypeId, operationType.getId());
    }

    @Test
    public void getPaymentIdSuccessfully() {
        int operationTypeId = OperationType.PAYMENT.getId();

        OperationType operationType = OperationType.getById(operationTypeId);

        assertEquals(operationTypeId, operationType.getId());
    }

    @Test
    public void getByIdUnsuccessfully() {
        int operationTypeId = -1;

        BusinessException businessException = assertThrows(BusinessException.class, () -> OperationType.getById(-1));

        assertEquals(String.format("The informed operation type [%s] doesn't exist.", operationTypeId), businessException.getMessage());
    }
}
