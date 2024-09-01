package com.project.transactions.service;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;
import com.project.transactions.domain.exception.BusinessException;
import com.project.transactions.mapper.AccountMapper;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.repository.AccountRepository;
import com.project.transactions.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private AccountRepository accountRepository;

    @SpyBean
    private AccountServiceImpl accountService;

    @Test
    public void createAccountSuccessfully() {
        AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();
        Account account = AccountMock.createAccount();

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse accountResponse = accountService.create(accountCreationRequest);

        assertEquals(account.getId(), accountResponse.getAccountId());
        assertEquals(account.getDocumentNumber(), accountResponse.getDocumentNumber());
    }

    @Test
    public void createAccountThenThrowsBusinessException() {
        AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();

        when(accountRepository.save(any(Account.class))).thenThrow(mock(DataAccessException.class));

        assertThrows(BusinessException.class, () -> accountService.create(accountCreationRequest));
    }

    @Test
    public void findByIdSuccessfully() {
        Long accountId = 1L;
        Account account = AccountMock.createAccount();

        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        accountService.findById(accountId);

        assertEquals(account.getId(), accountId);
    }

    @Test
    public void findByIdUnsuccessfully() {
        Long accountId = 1L;

        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        BusinessException businessException = assertThrows(BusinessException.class, () -> accountService.findById(accountId));

        assertEquals(String.format("Account with id: [%s] not found.", accountId), businessException.getMessage());
    }
}
