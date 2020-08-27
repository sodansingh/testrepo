package com.task.login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.task.login.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

    @Query("select u from User u where u.email=?1")
    User findFirstByEmail(String email);
   
    @Query("select u from User u where u.id=?1")
    User findUserById(int id);
}
