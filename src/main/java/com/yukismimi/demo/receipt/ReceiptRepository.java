package com.yukismimi.demo.receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    @Query(value = "select r from Receipt r where r.title like %?1%")
    Iterable<Receipt> findAllByTitleLike(String title);
}
