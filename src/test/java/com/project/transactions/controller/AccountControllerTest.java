package com.project.transactions.controller;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.service.AccountService;
import com.project.transactions.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void createUserSuccessfully() throws Exception {
        AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();
        AccountResponse accountResponse = AccountMock.createAccountResponse();

        when(accountService.create(any(AccountCreationRequest.class))).thenReturn(accountResponse);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(accountCreationRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account_id").value(accountResponse.getAccountId()))
                .andExpect(jsonPath("$.document_number").value(accountResponse.getDocumentNumber()));
    }

    @Test
    public void createUserAndReturnBadRequest() throws Exception {
        AccountCreationRequest accountCreationRequest = AccountMock.createWrongAccountCreationRequest();

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(accountCreationRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
