package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Receipt;
import com.lambdaschool.usermodel.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/receipts")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

//    Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    //localhost:2019/receipts/receipts
    @GetMapping(value = "/receipts", produces = "application/json")
    ResponseEntity<?> getReceiptsByUser(Authentication authentication){
        return new ResponseEntity<>(receiptService.getUserReceipts(authentication.getName()), HttpStatus.OK);
    }

//    //localhost:2019/receipts/receipts
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping(value = "/receipts", produces = "application/json")
//    ResponseEntity<?> getReceipts(){
//        return new ResponseEntity<>(receiptService.getReceipts(), HttpStatus.OK);
//    }

    //localhost:2019/receipts/receipt/{receiptid}
    @GetMapping(value = "/receipt/{receiptid}", produces = "application/json")
    ResponseEntity<?> findReceiptById(@PathVariable long receiptid, Authentication authentication){
        return new ResponseEntity<>(receiptService.findReceiptById(receiptid, authentication.getName()), HttpStatus.OK);
    }

//    //localhost:2019/receipts/receipt/{username}
//    @PostMapping(value = "/receipt/{username}", consumes = "application/json")
//    ResponseEntity<?> addReceipt(@Valid @RequestBody Receipt receipt, @PathVariable String username){
//        receiptService.addReceipt(username, receipt);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    //localhost:2019/receipts/receipt
    @PostMapping(value = "/receipt", consumes = "application/json")
    ResponseEntity<?> addReceipt(@Valid @RequestBody Receipt receipt, Authentication authentication){
        receiptService.addReceipt(authentication.getName(), receipt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //localhost:2019/receipts/receipt/{receiptid}
    @PutMapping(value = "/receipt/{receiptid}", consumes = "application/json")
    ResponseEntity<?> updateReceipt(@RequestBody Receipt receipt, @PathVariable long receiptid, Authentication authentication){
        receiptService.updateReceipt(receiptid, receipt, authentication.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //localhost:2019/receipts/receipt/delete/{receiptid}
    @DeleteMapping(value = "/receipt/delete/{receiptid}")
    ResponseEntity<?> deleteReceipt(@PathVariable long receiptid, Authentication authentication){
        receiptService.deleteReceipt(receiptid, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
