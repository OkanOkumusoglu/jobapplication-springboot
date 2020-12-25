package com.ok.dao.controllers.methods.jobadvert;

import com.ok.dao.CompanyRepository;
import com.ok.dao.JobAdvertRepository;
import com.ok.dao.entities.Company;
import com.ok.dao.entities.JobAdvert;
import com.ok.dao.entities.User;
import com.ok.dao.requests.jobadvert.AddJobAdvertLanguageRequest;
import com.ok.dao.requests.jobadvert.CreateJobAdvertRequest;
import com.ok.dao.requests.jobadvert.UpdateJobAdvertRequest;
import com.ok.dao.responses.jobadvert.CreateJobAdvertResponse;
import com.ok.dao.responses.jobadvert.UpdateJobAdvertResponse;

import java.util.List;

public class JobAdvertControllerMethods {

    public static void responseJobApplicants(User user, User registerUser, JobAdvert jobAdvert) {
        registerUser.setTalents(user.getTalents());
        registerUser.setAddress(user.getAddress());
        registerUser.setUserMail(user.getUserMail());
        registerUser.setUserPhone(user.getUserPhone());
        registerUser.setFirstName(user.getFirstName());
        registerUser.setLastName(user.getLastName());
        jobAdvert.getAcceptedUser().add(registerUser);
    }

    public static void updateJobAdvert(JobAdvert jobAdvert, UpdateJobAdvertRequest request, UpdateJobAdvertResponse response) {
        jobAdvert.setCountry(request.getCountry());
        jobAdvert.setAddress(request.getAddress());
        jobAdvert.setJobAdvertDescription(request.getJobAdvertDescription());
        jobAdvert.setDepartment(request.getDepartment());
    }

    public static void createJobAdvert(JobAdvert jobAdvert, Company company, JobAdvertRepository jobRepo, CompanyRepository compRepo,
                                       CreateJobAdvertRequest request, CreateJobAdvertResponse response) {
        jobAdvert.setCompanyNameBranchPosition(request.getCompanyNameBranchPosition());
        jobAdvert.setCountry(request.getCountry());
        jobAdvert.setDepartment(request.getDepartment());
        jobAdvert.setAddress(request.getAddress());
        jobAdvert.setJobAdvertDescription(request.getJobAdvertDescription());
        jobAdvert.getMinLanguageLevel().put(request.getLanguage(), request.getMinLanguageLevel());
        company.getJobAdverts().add(jobAdvert);
        jobRepo.save(jobAdvert);
        compRepo.save(company);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static int findJobAdvertInCompany(Company company, String companyNameBranchPosition) {
        for (int i = 0; i < company.getJobAdverts().size(); i++) {
            if (company.getJobAdverts().get(i).getCompanyNameBranchPosition().equals(companyNameBranchPosition)) {
                return i;
            }
        }
        return -1;
    }


    public static int findUserInApplicants(String userMail, List<User> applicants) {

        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getUserMail().equals(userMail)) {
                return i;
            }
        }
        return -1;
    }


}
