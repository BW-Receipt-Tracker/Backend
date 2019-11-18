package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceFoundException;
import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Receipt;
import com.lambdaschool.usermodel.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepo;

    @Autowired
    UserService userService;

    @Override
    public List<Receipt> getReceipts() {
        return receiptRepo.findAllBy();
    }

    @Override
    public Receipt findReceiptById(long receiptId) throws ResourceFoundException {
        return receiptRepo.findById(receiptId)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found"));
    }

    @Transactional
    @Override
    public Receipt addReceipt(long userid, Receipt receipt) {
        Receipt newReceipt = new Receipt();
        newReceipt.setAmount(receipt.getAmount());
        newReceipt.setMerchantname(receipt.getMerchantname());
        newReceipt.setImageurl(receipt.getImageurl());
        newReceipt.setDate(receipt.getDate());
        newReceipt.setCategory(receipt.getCategory());
        newReceipt.setUser(userService.findUserById(userid));

        return receiptRepo.save(newReceipt);
    }

    @Transactional
    @Override
    public Receipt updateReceipt(long receiptId, Receipt receipt) {
        Receipt currentReceipt = receiptRepo.findByReceiptid(receiptId);

        if (receipt.getCategory() != null){
            currentReceipt.setCategory(receipt.getCategory());
        }

        if (receipt.hasAmountSet){
            currentReceipt.setAmount(receipt.getAmount());
        }

        if (receipt.getDate() != null){
            currentReceipt.setDate(receipt.getDate());
        }

        if (receipt.getImageurl() != null){
            currentReceipt.setImageurl(receipt.getImageurl());
        }

        if (receipt.getMerchantname() != null){
            currentReceipt.setMerchantname(receipt.getMerchantname());
        }

        return receiptRepo.save(currentReceipt);
    }

    @Transactional
    @Override
    public void deleteReceipt(long receiptId) {
        Receipt receipt = receiptRepo.findByReceiptid(receiptId);

        if (receipt.getCategory() != null){
            receiptRepo.delete(receipt);
        }else{
            throw new ResourceNotFoundException("Receipt ID Does not Exist");
        }
    }
}
