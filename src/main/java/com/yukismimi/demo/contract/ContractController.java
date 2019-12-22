package com.yukismimi.demo.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public List<Contract> findByCondition(@RequestBody Contract contract){
        return contractService.findByCondition(contract);
    }

    @GetMapping("/contract")
    public List<Contract> findAll(){
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
        for (int i = 1; i <= 10; i++) {
            Contract contract = new Contract();
            contract.setContractName("title-"+i);
            contract.setContent("content-"+i);
            contract.setFirstParty("firstParty-"+i);
            contract.setSecondParty("secondParty-"+i);
            contract.setDueTime(Date.from(Instant.now().plus(i*2, ChronoUnit.DAYS)));
            contractService.saveContract(contract);
        }
    }
}
