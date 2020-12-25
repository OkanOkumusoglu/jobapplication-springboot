package com.ok.dao;

import com.ok.dao.entities.User;
import com.ok.dao.entities.UserCertificationInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserCertificationInformationRepository  extends MongoRepository<UserCertificationInformation, Integer> {

}
