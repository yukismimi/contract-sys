package com.yukismimi.demo.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin
@RestController
public class ReceiptController {

    private ReceiptServiceImpl receiptService;

    @PostMapping("/receipt")
    public Receipt saveReceipt(@RequestBody Receipt Receipt){
        return receiptService.saveReceipt(Receipt);
    }

    @GetMapping("/receipt/{id}")
    public Receipt findById(@PathVariable long id){
        return receiptService.findById(id);
    }

    @GetMapping("/receipt")
    public Iterable<Receipt> findAll(){
        return receiptService.findAll();
    }

    @DeleteMapping("/receipt/{id}")
    public void removeById(@PathVariable long id){
        receiptService.removeById(id);
    }

    @Autowired
    public ReceiptController(ReceiptServiceImpl receiptService) {
        this.receiptService = receiptService;
        initData();
    }

    private void initData(){
        for (int i = 1; i <= 10; i++) {
            Receipt receipt = new Receipt();
            receipt.setConsume(100);
            receipt.setTitle("title-"+i);
            receiptService.saveReceipt(receipt);
        }
    }
}
