package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Receipt;
import com.lambdaschool.usermodel.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/receipts")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = "/receipts", produces = "application/json")
    ResponseEntity<?> getReceipts(){
        return new ResponseEntity<>(receiptService.getReceipts(), HttpStatus.OK);
    }

    @GetMapping(value = "/receipt/{receiptid}", produces = "application/json")
    ResponseEntity<?> findReceiptById(@PathVariable long receiptid){
        return new ResponseEntity<>(receiptService.findReceiptById(receiptid), HttpStatus.OK);
    }

    @PostMapping(value = "/receipt/{userid}", consumes = "application/json")
    ResponseEntity<?> addReceipt(@Valid @RequestBody Receipt receipt, @PathVariable long userid){
        receiptService.addReceipt(userid, receipt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/receipt/{receiptid}", consumes = "application/json")
    ResponseEntity<?> updateReceipt(@RequestBody Receipt receipt, @PathVariable long receiptid){
        receiptService.updateReceipt(receiptid, receipt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/receipt/delete/{receiptid}")
    ResponseEntity<?> deleteReceipt(@PathVariable long receiptid){
        receiptService.deleteReceipt(receiptid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
