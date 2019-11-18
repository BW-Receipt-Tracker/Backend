package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    
}
