package com.yukismimi.demo.repository;

import com.yukismimi.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.name like %?1% or u.phone = ?2")
    Iterable<User> findAllByNameLikeOrPhone(String name, String phone);

    Optional<User> findByName(String name);
}
