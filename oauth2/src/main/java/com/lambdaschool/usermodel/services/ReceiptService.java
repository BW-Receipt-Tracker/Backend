package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Receipt;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public interface ReceiptService {
    List<Receipt> getUserReceipts(String username);

//    List<Receipt> getReceipts();

    Receipt findReceiptById(long receiptId, String username);

    Receipt addReceipt(String username, Receipt receipt);

    Receipt updateReceipt(long receiptId, Receipt receipt, String username);

    void deleteReceipt(long receiptId, String username);
}
