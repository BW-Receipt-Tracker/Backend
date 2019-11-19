package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceFoundException;
import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.exceptions.UserDoesntHavePermissionException;
import com.lambdaschool.usermodel.models.Receipt;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
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
    public List<Receipt> getUserReceipts(String username) {
        //User user = userService.findUserById(userid);
        User user = userService.findByName(username);
        return user.getReceipts();
    }

//    @Override
//    public List<Receipt> getReceipts() {
//        return receiptRepo.findAllBy();
//    }

    @Override
    public Receipt findReceiptById(long receiptId, String username) {
        Receipt receipt = receiptRepo.findById(receiptId)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found"));

        if (receipt.getUser().getUsername().equals(username)){
            return receipt;
        }else {
            throw new UserDoesntHavePermissionException("User doesn't have access to this receipt");
        }
    }

    @Transactional
    @Override
    public Receipt addReceipt(String username, Receipt receipt) {
        Receipt newReceipt = new Receipt();
        newReceipt.setAmount(receipt.getAmount());
        newReceipt.setMerchantname(receipt.getMerchantname());
        newReceipt.setImageurl(receipt.getImageurl());
        newReceipt.setDate(receipt.getDate());
        newReceipt.setCategory(receipt.getCategory());
        if (userService.findByName(username) != null){
            newReceipt.setUser(userService.findByName(username));
        }
        else {
            throw new ResourceNotFoundException("Username is not correct");
        }

        return receiptRepo.save(newReceipt);
    }

    @Transactional
    @Override
    public Receipt updateReceipt(long receiptId, Receipt receipt, String username) {
        Receipt currentReceipt = receiptRepo.findByReceiptid(receiptId);

        if (currentReceipt.getUser().getUsername().equals(username)){
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
        }else {
            throw new UserDoesntHavePermissionException("User doesn't have permission to change this receipt");
        }
    }

    @Transactional
    @Override
    public void deleteReceipt(long receiptId, String username) {
        Receipt receipt = receiptRepo.findByReceiptid(receiptId);

        if (receipt.getUser().getUsername().equals(username)){
            if (receipt.getCategory() != null){
                receiptRepo.delete(receipt);
            }else{
                throw new ResourceNotFoundException("Receipt ID Does not Exist");
            }
        }else {
            throw new UserDoesntHavePermissionException("User doesn't have permission to delete this receipt");
        }

    }
}
