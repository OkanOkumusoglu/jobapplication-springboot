package com.ok.dao;

import com.ok.dao.entities.JobAdvert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface JobAdvertRepository extends MongoRepository<JobAdvert, String> {

    @Query("{ 'companyNameBranchPosition' : { $regex: '?0', $options: 'i'} }")
    List<JobAdvert> findByCompanyNameBranchPosition(String companyNameBranchPosition);


    @Query("{ 'country' : { $regex: '?0', $options: 'i'} }")
    List<JobAdvert> findByCountry(String country);

    @Query("{ 'address' : { $regex: '?0', $options: 'i'} }")
    List<JobAdvert> findByAddress(String address);


}
