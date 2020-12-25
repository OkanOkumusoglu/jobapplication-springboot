package com.ok.dao;

import com.ok.dao.entities.Company;
import com.ok.dao.entities.JobAdvert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CompanyRepository extends MongoRepository<Company, String> {


    @Override
    List<Company> findAll();

    @Query("{ 'companyName' : { $regex: '?0', $options: 'i'} }")
    List<Company> findByCompanyName(String companyName);


    @Query("{ 'companySector' : { $regex: '?0', $options: 'i'} }")
    List<Company> findByCompanySector(String companySector);


}
