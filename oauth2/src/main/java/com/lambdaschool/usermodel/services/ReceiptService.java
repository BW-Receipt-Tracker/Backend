package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Receipt;

import java.util.List;

public interface ReceiptService {
    List<Receipt> getReceipts();

    Receipt findReceiptById(long receiptId);

    Receipt addReceipt(Receipt receipt);

    Receipt updateReceipt(long receiptId, Receipt receipt);

    void deleteReceipt(long receiptId);
}
