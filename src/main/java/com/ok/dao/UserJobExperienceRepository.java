package com.ok.dao;

import com.ok.dao.entities.UserJobExperience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserJobExperienceRepository extends MongoRepository<UserJobExperience, Integer> {
}
