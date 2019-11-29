package com.yukismimi.demo.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ContractController {

    private ContractServiceImpl contractService;

    @PostMapping("/contract")
    public Contract saveContract(@RequestBody Contract contract){
        return contractService.saveContract(contract);
    }

    @GetMapping("/contract/{id}")
    public Contract findById(@PathVariable long id){
        return contractService.findById(id);
    }

    @PostMapping("/contract/condition")
    public Iterable<Contract> findByCondition(@RequestBody Contract contract){
        return contractService.findByCondition(contract);
    }

    @GetMapping("/contract")
    public Iterable<Contract> findAll(){
        return contractService.findAll();
    }

    @DeleteMapping("/contract/{id}")
    public void removeById(@PathVariable long id){
        contractService.removeById(id);
    }

    @Autowired
    public ContractController(ContractServiceImpl contractService) {
        this.contractService = contractService;
        initData();
    }

    private void initData() {
        for (int i = 1; i <= 100; i++) {
            Contract contract = new Contract();
            contract.setContractName("title-"+i);
            contract.setContent("content-"+i);
            contract.setFirstParty("firstParty-"+i);
            contract.setSecondParty("secondParty-"+i);
            contractService.saveContract(contract);
        }
    }
}
