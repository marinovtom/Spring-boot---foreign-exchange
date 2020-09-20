package com.example.exchange.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.exchange.openfeign.CurrencyPairClient;
import com.example.exchange.repo.ConversionRepository;
import com.example.exchange.server.ListController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ListController.class)
public class ListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	ListController controller;
	
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
	public void requiredParams() throws Exception
	{
		this.mockMvc.perform(get("/exchange/list")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("/exchange/list must have one of two parameters (id or date)!")));
	}
	
	@Test
	public void dateFormat() throws Exception
	{
		this.mockMvc.perform(get("/exchange/list?date=1.1.2020")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Date must be in format: dd/MM/yyyy")));
	}
}