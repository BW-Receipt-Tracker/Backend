package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Override
    public List<Receipt> getReceipts() {
        return null;
    }

    @Override
    public Receipt findReceiptById(long receiptId) {
        return null;
    }

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return null;
    }

    @Override
    public Receipt updateReceipt(long receiptId, Receipt receipt) {
        return null;
    }

    @Override
    public void deleteReceipt(long receiptId) {

    }
}
