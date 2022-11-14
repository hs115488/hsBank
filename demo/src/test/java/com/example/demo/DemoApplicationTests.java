package com.example.demo;

import com.example.demo.api.Account.Account;
import com.example.demo.api.Account.AccountController;
import com.example.demo.api.Account.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountController accountController;

	@MockBean
	private AccountRepository accountRepository;

	private ObjectMapper mapper;
	@Test
	public void isAccountCreated() throws Exception {
		Map<String, String> body = new HashMap<String, String>();
		body.put("firstName", "Haresh");
		body.put("lastName", "Singh");
		body.put("accountType", "checking");

		mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/createAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(body))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void isAccountDeleted() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content("1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content(String.valueOf("ax3"))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
