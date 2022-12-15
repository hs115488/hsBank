package com.example.demo;

import com.example.demo.api.Account.Account;
import com.example.demo.api.Account.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccountRepository accountRepository;
	private ObjectMapper mapper;


	@Test
	@Order(1)
	public void isAccountCreated() throws Exception {
		Map<String, String> body = new HashMap<String, String>();
		body.put("firstName", "Haresh");
		body.put("lastName", "Singh");
		body.put("accountType", "checking");

		mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/createAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(body))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	@Test
	@Order(2)
	public void IsDeposited() throws Exception{
		mapper = new ObjectMapper();
		Map<String, Integer> body = new HashMap<>();
		body.put("id", (((List<Account>) accountRepository.findAll()).get(0)).getId());
		body.put("amount", 5000);

		mockMvc.perform(MockMvcRequestBuilders
					.put("/deposit")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(body)))
				.andExpect(status().isAccepted());
	}

	@Test
	@Order(3)
	public void IsWithdrawn() throws Exception{
		mapper = new ObjectMapper();
		Map<String, Integer> body = new HashMap<>();
		body.put("id", (((List<Account>) accountRepository.findAll()).get(0)).getId());
		body.put("amount" ,500);

		mockMvc.perform(MockMvcRequestBuilders
						.put("/withdraw")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(body)))
				.andExpect(status().isAccepted());
	}

	@Test
	@Order(4)
	public void isAccountDeleted() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content(String.valueOf((((List<Account>) accountRepository.findAll()).get(1)).getId()))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAccount")
						.contentType(MediaType.APPLICATION_JSON)
						.content(String.valueOf("ax3"))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}




}

