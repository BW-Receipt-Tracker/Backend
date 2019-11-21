package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Receipt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    List<Receipt> findAllBy();

    Receipt findByReceiptid(long receiptId);

    @Modifying
    @Query(value = "DELETE FROM receipts WHERE receiptid = :receiptId", nativeQuery = true)
    void removeReciept(long receiptId);
}
