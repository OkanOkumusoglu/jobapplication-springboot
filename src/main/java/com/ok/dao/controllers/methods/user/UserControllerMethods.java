package com.ok.dao.controllers.methods.user;

import com.ok.dao.JobAdvertRepository;
import com.ok.dao.UserCertificationInformationRepository;
import com.ok.dao.UserJobExperienceRepository;
import com.ok.dao.UserRepository;
import com.ok.dao.entities.*;
import com.ok.dao.requests.user.*;
import com.ok.dao.responses.user.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserControllerMethods {


    public static void userApplyJob(User registerUser, User user, JobAdvert jobAdvert, JobAdvert registerJobAdvert, UserRepository userRepo,
                                    JobAdvertRepository jobRepo, UserApplyJobResponse response) {
        registerUser.setUserPhone(user.getUserPhone());
        registerUser.setFirstName(user.getFirstName());
        registerUser.setLastName(user.getLastName());
        registerUser.setUserMail(user.getUserMail());
        registerUser.setTalents(user.getTalents());
        registerUser.setUserJobExperiences(user.getUserJobExperiences());
        registerUser.setUserCertificationsInformation(user.getUserCertificationsInformation());
        registerUser.setUserEducationInformation(user.getUserEducationInformation());
        registerUser.setUserGeneralInformation(user.getUserGeneralInformation());
        registerJobAdvert.setAddress(jobAdvert.getAddress());
        registerJobAdvert.setCompanyNameBranchPosition(jobAdvert.getCompanyNameBranchPosition());
        registerJobAdvert.setMinLanguageLevel(jobAdvert.getMinLanguageLevel());
        registerJobAdvert.setDepartment(jobAdvert.getDepartment());
        registerJobAdvert.setCountry(jobAdvert.getCountry());
        jobAdvert.getApplicants().add(registerUser);
        user.getAppliedJobs().add(registerJobAdvert);
        userRepo.save(user);
        jobRepo.save(jobAdvert);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void updateUser(User user, UserRepository userRepo, UpdateUserRequest request, UpdateUserResponse response) {
        user.setAddress(request.getAddress());
        user.setBirthday(request.getBirthday());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepo.save(user);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void userFollowingJobAdvert(User user, JobAdvert jobAdvert, JobAdvert registerJobAdvert, UserRepository userRepo,
                                              UserFollowJobAdvertResponse response) {
        registerJobAdvert.setDepartment(jobAdvert.getDepartment());
        registerJobAdvert.setJobAdvertDescription(jobAdvert.getJobAdvertDescription());
        registerJobAdvert.setAddress(jobAdvert.getAddress());
        registerJobAdvert.setMinLanguageLevel(jobAdvert.getMinLanguageLevel());
        registerJobAdvert.setCompanyNameBranchPosition(jobAdvert.getCompanyNameBranchPosition());
        registerJobAdvert.setCountry(jobAdvert.getCountry());
        user.getFollowingJobAdverts().add(registerJobAdvert);
        userRepo.save(user);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void createUser(User user, UserRepository userRepo, CreateUserRequest request, CreateUserResponse response) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
        user.setUserMail(request.getUserMail());
        String encodedPassword = encoder.encode(request.getUserPassword());
        user.setUserPassword(encodedPassword);
        user.setAddress(request.getAddress());
        user.setBirthday(request.getBirthday());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserPhone(request.getUserPhone());
        userRepo.save(user);
        response.setSuccess(true);
        response.setMessage("Created");
    }

    public static void addUserCertification(User user, UserRepository userRepo, UserCertificationInformationRepository certRepo,
                                            AddCertificationInformationRequest request, AddCertificationInformationResponse response) {

        UserCertificationInformation userCertificationInformation = new UserCertificationInformation(request.getCertificationName(), request.getUserMail(),
                request.getInstitution(), request.getCertificationDate());
        user.getUserCertificationsInformation().add(userCertificationInformation);
        userRepo.save(user);
        certRepo.save(userCertificationInformation);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static int findTalent(List<String> talents, String talent) {

        for (int i = 0; i < talents.size(); i++) {
            if (talents.get(i).equals(talent)) {
                return i;
            }
        }
        return -1;
    }

    public static void addUserGeneralInformation(User user, UserRepository userRepo,
                                                 AddUserGeneralInformationRequest request, AddUserGeneralInformationResponse response) {
        UserGeneralInformation userGeneralInformation = new UserGeneralInformation(request.getNationality(), request.getWageExpectation(),
                request.getDriverLicence(), request.getGender(), request.getSummary());
        userRepo.save(user);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void addUserJobExperience(User user, UserRepository userRepo, UserJobExperienceRepository userJobRepo,
                                            AddUserJobExperienceRequest request, AddUserJobExperienceResponse response) {
        UserJobExperience userJobExperience = new UserJobExperience(request.getUserMail(), request.getCompanyName(), request.getPosition(),
                request.getStartDate(), request.getEndDate(), request.getCompanySector(), request.getCountry(), request.getJobDefinition());
        user.getUserJobExperiences().add(userJobExperience);
        userRepo.save(user);
        userJobRepo.save(userJobExperience);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void addUserEducationInformation(User user, UserRepository userRepo,
                                                   AddUserEducationInformationRequest request, AddUserEducationInformationResponse response) {
        UserEducationInformation userEducationInformation = new UserEducationInformation(request.getCollege(), request.getFaculty(), request.getDepartment(),
                request.getStartDate(), request.getEndDate(), request.getEducationLanguage(), request.getGpa());

        user.setUserEducationInformation(userEducationInformation);

        userRepo.save(user);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static int findUserCertification(List<UserCertificationInformation> userCertificationInformation, int userCertificationId) {
        for (int i = 0; i < userCertificationInformation.size(); i++) {
            if (userCertificationInformation.get(i).getCertificationId() == userCertificationId) {
                return i;
            }
        }
        return -1;
    }


    public static int findCertification(UserCertificationInformation certificationInformation, List<UserCertificationInformation> certificationList) {

        for (int i = 0; i < certificationList.size(); i++) {
            UserCertificationInformation certification = certificationList.get(i);
            if (certification.getCertificationName().equals(certificationInformation.getCertificationName()) &&
                    certification.getCertificationDate().equals(certificationInformation.getCertificationDate()) &&
                    certification.getInstitution().equals(certificationInformation.getInstitution())) {
                return i;
            }
        }
        return -1;
    }
}
