package com.yukismimi.demo.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "select c from Contract c where c.contractName like %?1%")
    Iterable<Contract> findAllByContractNameLike(String contractName);
}
