package com.yukismimi.demo.service;

import com.yukismimi.demo.entity.Contract;
import com.yukismimi.demo.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class ContractServiceImpl {

    private ContractRepository contractRepository;

    public Contract saveContract(Contract contract){
        Duration timeElapsed = Duration.between(Instant.now(), contract.getDueTime().toInstant());
        boolean expired = timeElapsed.toDays() < 0;
        boolean willExpire = !expired && timeElapsed.toDays() <= 7;
        contract.setExpired(expired);
        contract.setWillExpire(willExpire);
        return contractRepository.save(contract);
    }

    public Contract findById(long id){
        return contractRepository.findById(id).orElse(null);
    }

    public List<Contract> findByCondition(Contract contract){
        return contractRepository.findAllByContractNameLike(contract.getContractName());
    }

    public List<Contract> findAll(){
        return contractRepository.findAll();
    }

    public void removeById(long id){
        contractRepository.deleteById(id);
    }

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
}
