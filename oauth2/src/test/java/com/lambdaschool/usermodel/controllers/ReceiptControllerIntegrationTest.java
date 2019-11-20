package com.lambdaschool.usermodel.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReceiptControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void  setUp(){
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void measureResponseTime() throws Exception{
        long time = System.currentTimeMillis();
        this.mockMvc.perform(get("/receipts/receipts")).andDo(print());
        long responseTime = System.currentTimeMillis() - time;
        assertTrue(responseTime < 5000L);
        //given().when().get("/receipts/receipts").then().time(lessThan(5000L));
    }

    @Test
    public void getAllReceipts() throws Exception{
        this.mockMvc.perform(get("/receipts/receipts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Entertainment")));
    }


}
