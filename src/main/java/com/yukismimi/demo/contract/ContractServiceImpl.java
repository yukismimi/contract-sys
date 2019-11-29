package com.yukismimi.demo.contract;

import com.yukismimi.demo.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl {

    private ContractRepository contractRepository;

    public Contract saveContract(Contract contract){
        return contractRepository.save(contract);
    }

    public Contract findById(long id){
        return contractRepository.findById(id).orElse(null);
    }

    public Iterable<Contract> findByCondition(Contract contract){
        return contractRepository.findAllByContractNameLike(contract.getContractName());
    }

    public Iterable<Contract> findAll(){
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
