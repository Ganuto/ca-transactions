package com.project.transactions.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private AccountService accountService;

  @Test
  public void createUserSuccessfully() throws Exception {
    AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();
    AccountResponse accountResponse = AccountMock.createAccountResponse();

    when(accountService.create(any(AccountCreationRequest.class))).thenReturn(accountResponse);

    mockMvc
        .perform(
            post("/accounts")
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

    mockMvc
        .perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertObjectToJsonString(accountCreationRequest)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.timestamp").exists())
        .andExpect(jsonPath("$.http_code").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.http_status").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
        .andExpect(
            jsonPath("$.error_message")
                .value("document_number must match the expression '^[0-9]*$' (number only)."))
        .andDo(print());
  }

  @Test
  public void getUserSuccessfully() throws Exception {
    AccountResponse accountResponse = AccountMock.createAccountResponse();

    when(accountService.findById(any(Long.class))).thenReturn(accountResponse);

    mockMvc
        .perform(get("/accounts").contentType(MediaType.APPLICATION_JSON).param("accountId", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.account_id").value(accountResponse.getAccountId()))
        .andExpect(jsonPath("$.document_number").value(accountResponse.getDocumentNumber()));
  }

  @Test
  public void getUserWithStringAccountIDAndReturnBadRequest() throws Exception {
    String wrongAccountId = "NOT_A_NUMBER";
    mockMvc
        .perform(
            get("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountId", wrongAccountId))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.timestamp").exists())
        .andExpect(jsonPath("$.http_code").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.http_status").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
        .andExpect(
            jsonPath("$.error_message")
                .value(
                    String.format(
                        "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"%s\"",
                        wrongAccountId)));
  }

  @Test
  public void getUserWithNegativeAccountIdAndReturnBadRequest() throws Exception {
    mockMvc
        .perform(get("/accounts").contentType(MediaType.APPLICATION_JSON).param("accountId", "-1"))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.timestamp").exists())
        .andExpect(jsonPath("$.http_code").value(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.http_status").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
        .andExpect(jsonPath("$.error_message").value("accountId cannot be negative or zero"));
  }
}
