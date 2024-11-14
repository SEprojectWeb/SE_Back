package com.CompareElec.CompareElec.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserid(String userid);
    Optional<User> findByNameAndPhonenumber(String name, String phonenumber);
    Optional<User> findByUseridAndNameAndPhonenumber(String userid,String name, String phonenumber);
}
