package com.yukismimi.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.name like %?1% or u.phone = ?2")
    Iterable<User> findAllByNameLikeOrPhone(String name, String phone);
}
