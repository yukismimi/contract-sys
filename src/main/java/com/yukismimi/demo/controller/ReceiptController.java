package com.yukismimi.demo.controller;

import com.yukismimi.demo.entity.Receipt;
import com.yukismimi.demo.service.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class ReceiptController {

    private ReceiptServiceImpl receiptService;

    @PostMapping("/receipt")
    public Receipt saveReceipt(@RequestBody Receipt receipt){
        return receiptService.saveReceipt(receipt);
    }

    @GetMapping("/receipt/{id}")
    public Receipt findById(@PathVariable long id){
        return receiptService.findById(id);
    }

    @PostMapping("/receipt/condition")
    public Iterable<Receipt> findByCondition(@RequestBody Receipt receipt){
        return receiptService.findByCondition(receipt);
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
        for (int i = 1; i <= 100; i++) {
            Receipt receipt = new Receipt();
            receipt.setConsume(100);
            receipt.setTitle("title-"+i);
            receiptService.saveReceipt(receipt);
        }
    }
}
