package com.lambdaschool.usermodel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.usermodel.models.*;
import com.lambdaschool.usermodel.services.ReceiptService;
import com.lambdaschool.usermodel.services.RoleService;
import com.lambdaschool.usermodel.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin",
        roles = {"USER", "ADMIN"})
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class ReceiptControllerUnitTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    ReceiptService receiptService;

    @MockBean
    UserService userService;

    @MockBean
    RoleService roleService;

    private User user;
    private List<Receipt> listReceipts = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                r1));
        admins.add(new UserRoles(new User(),
                r2));
        admins.add(new UserRoles(new User(),
                r3));
        user = new User("admin",
                "ILuvM4th!",
                "admin@lambdaschool.local",
                admins);
        user.getUseremails()
                .add(new Useremail(user,
                        "admin@email.local"));
        user.getUseremails()
                .add(new Useremail(user,
                        "admin@mymail.local"));
        user = userService.save(user);

        Receipt receipt = new Receipt("November",
                22.06,
                "Entertainment",
                "Megaplex",
                "url",
                user);
        receipt.setReceiptid(1);
        listReceipts.add(receipt);

        receiptService.addReceipt("admin", receipt);
        user = userService.save(user);

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getReceiptsByUser() throws Exception {
        Mockito.when(receiptService.getUserReceipts("admin")).thenReturn(listReceipts);

        String url = "/receipts/receipts";

        RequestBuilder rb = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(rb).andReturn();
        String actual = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(listReceipts);

        assertEquals(expected, actual);
    }

    @Test
    public void findReceiptById()throws Exception {
        Mockito.when(receiptService.findReceiptById(1, "admin")).thenReturn(listReceipts.get(0));

        String url = "/receipts/receipt/1";

        RequestBuilder rb = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(rb).andReturn();
        String actual = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(listReceipts.get(0));

        assertEquals(expected, actual);
    }

    @Test
    public void addReceipt() throws Exception{
        Receipt newReceipt = new Receipt("December",
                22.06,
                "Entertainment",
                "Megaplex",
                "url",
                user);

        Mockito.when(receiptService.addReceipt("admin", newReceipt)).thenReturn(newReceipt);

        String url = "/receipts/receipt";

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(newReceipt);

        RequestBuilder rb = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateReceipt() throws Exception{
        Receipt newReceipt = new Receipt();
        newReceipt.setDate("11/16/2019");

        Receipt updatedReceipt = listReceipts.get(0);
        updatedReceipt.setDate("11/16/2019");

        Mockito.when(receiptService.updateReceipt(1, newReceipt,"admin")).thenReturn(updatedReceipt);

        String url = "/receipts/receipt/1";

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(newReceipt);

        RequestBuilder rb = MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void Z_deleteReceipt() throws Exception{
        String url = "/receipts/receipt/delete/1";

        RequestBuilder rb = MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}