package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Receipt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    /*
    List<Receipt> getReceipts();

    Receipt findReceiptById(long receiptId);

    Receipt addReceipt(Receipt receipt);

    Receipt updateReceipt(long receiptId, Receipt receipt);

    void deleteReceipt(long receiptId);
     */

    List<Receipt> getAll();

    Receipt findByReceiptid(long receiptId);
}
