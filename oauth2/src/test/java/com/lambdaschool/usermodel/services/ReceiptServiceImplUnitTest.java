package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Receipt;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReceiptServiceImplUnitTest {
    @Autowired
    private ReceiptService receiptService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void A_getUserReceipts() {
        //System.out.println(receiptService.getUserReceipts("admin").get(0).getReceiptid());
        assertEquals(1, receiptService.getUserReceipts("admin").size());
    }

    @Test
    public void findReceiptById() {
        assertEquals("Entertainment", receiptService.findReceiptById(7, "admin").getCategory());
    }

    @Test
    public void addReceipt() {
        Receipt newReceipt = new Receipt("NovemberIsBest",
                22.06,
                "Entertainment",
                "Megaplex",
                "url",
                receiptService.getUserReceipts("admin").get(0).getUser());
        receiptService.addReceipt("admin", newReceipt);
        assertEquals(2, receiptService.getUserReceipts("admin").size());
    }

    @Test
    public void updateReceipt() {
        Receipt newReceipt = new Receipt();
        newReceipt.setImageurl("image");
        assertEquals("image", receiptService.updateReceipt(7, newReceipt, "admin").getImageurl());
    }

    @Test
    public void Z_deleteReceipt() {
        receiptService.deleteReceipt(7, "admin");
        assertEquals(1, receiptService.getUserReceipts("admin").size());
    }
}