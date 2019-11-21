package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Receipt;
import com.lambdaschool.usermodel.services.ReceiptService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/receipts")
@Api(tags = "ReceiptEndpoints")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    //localhost:2019/receipts/receipts
    @ApiOperation(value = "returns all User Specific receipts", response = Receipt.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Receipts Returned", response = Receipt.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping(value = "/receipts", produces = "application/json")
    ResponseEntity<?> getReceiptsByUser(Authentication authentication) {
        logger.info("User: " + authentication.getName() + " requested all their receipts");
        return new ResponseEntity<>(receiptService.getUserReceipts(authentication.getName()), HttpStatus.OK);
    }

    //localhost:2019/receipts/receipt/{receiptid}
    @ApiOperation(value = "Only Returns User Specific receipt by ID", response = Receipt.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Receipt Returned", response = Receipt.class),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping(value = "/receipt/{receiptid}", produces = "application/json")
    ResponseEntity<?> findReceiptById(@ApiParam(value = "Receipt ID", required = true, example = "4") @PathVariable long receiptid, Authentication authentication) {
        logger.info("User: " + authentication.getName() + " requested receipt by id " + receiptid);
        return new ResponseEntity<>(receiptService.findReceiptById(receiptid, authentication.getName()), HttpStatus.OK);
    }

    //localhost:2019/receipts/receipt
    @ApiOperation(value = "Creates a new receipt tied to user.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Receipt Created")
    })
    @PostMapping(value = "/receipt", consumes = "application/json")
    ResponseEntity<?> addReceipt(@ApiParam(value = "Full Receipt, Image not Required", required = true)@Valid @RequestBody Receipt receipt, Authentication authentication) {
        logger.info("User: " + authentication.getName() + " made api call to add a receipt");
        receiptService.addReceipt(authentication.getName(), receipt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //localhost:2019/receipts/receipt/{receiptid}
    @ApiOperation(value = "Only Updates User Specific receipt by ID and changed fields")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Receipt Updated"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PutMapping(value = "/receipt/{receiptid}", consumes = "application/json")
    ResponseEntity<?> updateReceipt(@ApiParam(value = "Receipt fields needing an update", required = true)@RequestBody Receipt receipt, @ApiParam(value = "Receipt ID", required = true, example = "4")@PathVariable long receiptid, Authentication authentication) {
        logger.info("User: " + authentication.getName() + " made an api call to update receipt of id " + receiptid);
        receiptService.updateReceipt(receiptid, receipt, authentication.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //localhost:2019/receipts/receipt/delete/{receiptid}
    @ApiOperation(value = "Only Deletes User Specific receipt by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Receipt Deleted"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping(value = "/receipt/delete/{receiptid}")
    ResponseEntity<?> deleteReceipt(@ApiParam(value = "Receipt ID", required = true, example = "4")@PathVariable long receiptid, Authentication authentication) {
        logger.info("User: " + authentication.getName() + " made a call to delete receipt of id " + receiptid);
        receiptService.deleteReceipt(receiptid, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
