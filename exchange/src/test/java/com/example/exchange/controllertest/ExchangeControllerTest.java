package com.example.exchange.controllertest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

import com.example.exchange.model.Conversion;
import com.example.exchange.openfeign.CurrencyPairClient;
import com.example.exchange.repo.ConversionRepository;
import com.example.exchange.server.ExchangeController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	ExchangeController controller;
	
	@MockBean
	CurrencyPairClient client;
	
	@MockBean
	ConversionRepository repository;
	
	@Test
	public void contextLoads() throws Exception
	{
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void exchangeParams() throws Exception
	{
		this.mockMvc.perform(get("/exchange")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("(base, exchangeString)")));
		
		this.mockMvc.perform(get("/exchange?base=USD")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Parameter exchangeString cannot be empty!")));
		
		this.mockMvc.perform(get("/exchange?exchangeString=USD")).andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("Parameter base cannot be empty!")));
	}
}