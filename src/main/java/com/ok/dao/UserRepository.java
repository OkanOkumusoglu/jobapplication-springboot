package com.ok.dao;

import com.ok.dao.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends MongoRepository<User, String> {


    @Override
    List<User> findAll();

    @Query("{ 'userMail' : { $regex: '?0', $options: 'i'} }")
    List<User> findByUserMail(String userMail);


    @Query("{ 'firstName' : { $regex: '?0', $options: 'i'} }")
    List<User> findByFirstName(String firstName);

    @Query("{ 'lastName' : { $regex: '?0', $options: 'i'} }")
    List<User> findByLastName(String lastName);

}
