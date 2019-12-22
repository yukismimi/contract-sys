package com.yukismimi.demo.contract;

import com.yukismimi.demo.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ContractServiceImpl {

    private ContractRepository contractRepository;

    public Contract saveContract(Contract contract){
        Duration timeElapsed = Duration.between(Instant.now(), contract.getDueTime().toInstant());
        if (timeElapsed.toDays() <= 7)
            contract.setWillExpire(true);
        else
            contract.setWillExpire(false);
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
