package com.yukismimi.demo.service;

import com.yukismimi.demo.entity.Receipt;
import com.yukismimi.demo.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl {

    private ReceiptRepository receiptRepository;

    public Receipt saveReceipt(Receipt Receipt){
        return receiptRepository.save(Receipt);
    }

    public Receipt findById(long id){
        return receiptRepository.findById(id).orElse(null);
    }

    public Iterable<Receipt> findByCondition(Receipt receipt){
        return receiptRepository.findAllByTitleLike(receipt.getTitle());
    }

    public Iterable<Receipt> findAll(){
        return receiptRepository.findAll();
    }

    public void removeById(long id){
        receiptRepository.deleteById(id);
    }

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }
}
