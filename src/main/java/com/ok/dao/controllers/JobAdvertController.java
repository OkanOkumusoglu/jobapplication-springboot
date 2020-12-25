package com.ok.dao.controllers;

import com.ok.dao.CompanyRepository;
import com.ok.dao.JobAdvertRepository;
import com.ok.dao.UserRepository;
import com.ok.dao.controllers.methods.jobadvert.JobAdvertControllerMethods;
import com.ok.dao.entities.Company;
import com.ok.dao.entities.JobAdvert;
import com.ok.dao.entities.User;
import com.ok.dao.requests.jobadvert.CreateJobAdvertRequest;
import com.ok.dao.requests.jobadvert.AddJobAdvertLanguageRequest;
import com.ok.dao.requests.jobadvert.ResponseJobApplicantsRequest;
import com.ok.dao.requests.jobadvert.UpdateJobAdvertRequest;
import com.ok.dao.requests.user.UpdateUserRequest;
import com.ok.dao.responses.jobadvert.CreateJobAdvertResponse;
import com.ok.dao.responses.jobadvert.AddJobAdvertLanguageResponse;
import com.ok.dao.responses.jobadvert.ResponseJobApplicantsResponse;
import com.ok.dao.responses.jobadvert.UpdateJobAdvertResponse;
import com.ok.dao.responses.user.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/job-advert")
public class JobAdvertController {

    @Autowired
    JobAdvertRepository jobRepo;

    @Autowired
    CompanyRepository compRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/find-by-company-name/{companyNameBranchPosition}")
    public List<JobAdvert> findJobAdvertsByCompanyNameBranchPosition(@PathVariable String companyNameBranchPosition) {
        return jobRepo.findByCompanyNameBranchPosition(companyNameBranchPosition);
    }


    @GetMapping("/find-by-country/{country}")
    public List<JobAdvert> findJobAdvertsByCountry(@PathVariable String country) {
        return jobRepo.findByCountry(country);
    }

    @GetMapping("/find-by-address/{address}")
    public List<JobAdvert> findJobAdvertsByAddress(@PathVariable String address) {
        return jobRepo.findByAddress(address);
    }

    @PostMapping("/update")
    public UpdateJobAdvertResponse updateUser(@RequestBody UpdateJobAdvertRequest request) {
        UpdateJobAdvertResponse response = new UpdateJobAdvertResponse();

        if (jobRepo.existsById(request.getCompanyNameBranchPosition())) {
            if (compRepo.existsById(request.getCompanyName())) {
                Company company = compRepo.findById(request.getCompanyName()).get();
                JobAdvert jobAdvert = jobRepo.findById(request.getCompanyNameBranchPosition()).get();
                JobAdvertControllerMethods.updateJobAdvert(jobAdvert, request, response);
                int deleteIndex = JobAdvertControllerMethods.findJobAdvertInCompany(company, request.getCompanyNameBranchPosition());
                if (deleteIndex != -1) {
                    company.getJobAdverts().remove(deleteIndex);
                    company.getJobAdverts().add(jobAdvert);
                    compRepo.save(company);

                }
                jobRepo.save(jobAdvert);

                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Company doesn't exist!");
            }

        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/add-language")
    public AddJobAdvertLanguageResponse addJobAdvertLanguage(@RequestBody AddJobAdvertLanguageRequest request) {
        AddJobAdvertLanguageResponse response = new AddJobAdvertLanguageResponse();

        if (jobRepo.existsById(request.getCompanyNameBranchPosition())) {
            JobAdvert jobAdvert = jobRepo.findById(request.getCompanyNameBranchPosition()).get();
            if (!jobAdvert.getMinLanguageLevel().containsKey(request.getLanguage())) {
                if (request.getMinLanguageLevel() > 0 && request.getMinLanguageLevel() <= 5) {
                    Company company = compRepo.findById(request.getCompanyName()).get();
                    int jobAdvertIndexNumber = JobAdvertControllerMethods.findJobAdvertInCompany(company, request.getCompanyNameBranchPosition());
                    if (jobAdvertIndexNumber != -1) {
                        company.getJobAdverts().get(jobAdvertIndexNumber).getMinLanguageLevel().put(request.getLanguage(),
                                request.getMinLanguageLevel());
                        compRepo.save(company);
                    }
                    jobAdvert.getMinLanguageLevel().put(request.getLanguage(), request.getMinLanguageLevel());
                    jobRepo.save(jobAdvert);
                    response.setSuccess(true);
                    response.setMessage("Successful!");
                } else {
                    response.setSuccess(false);
                    response.setMessage("Language level must be between 0-5");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("Language already exist!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Job advert doesn't exist!");
        }
        return response;
    }

    @PostMapping("/response-job-applicants")
    public ResponseJobApplicantsResponse responseJobApplicants(@RequestBody ResponseJobApplicantsRequest request) {
        ResponseJobApplicantsResponse response = new ResponseJobApplicantsResponse();
        if (jobRepo.existsById(request.getCompanyNameBranchPosition())) {
            if (userRepo.existsById(request.getUserMail())) {
                User user = userRepo.findById(request.getUserMail()).get();
                JobAdvert jobAdvert = jobRepo.findById(request.getCompanyNameBranchPosition()).get();
                user.getAppliedJobResponses().put(request.getCompanyNameBranchPosition(), request.getResponse());
                int userRemoveIndex = JobAdvertControllerMethods.findUserInApplicants(request.getUserMail(), jobAdvert.getApplicants());
                jobAdvert.getApplicants().remove(userRemoveIndex);
                if (request.getResponse() == true) {
                    User registerUser = new User();
                    JobAdvertControllerMethods.responseJobApplicants(user, registerUser, jobAdvert);
                }

                jobRepo.save(jobAdvert);
                userRepo.save(user);
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("User doesn't exist!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Job advert doesn't exist!");
        }
        return response;
    }

    @PostMapping("/create")
    public CreateJobAdvertResponse addJobAdverts(@RequestBody CreateJobAdvertRequest request) {
        CreateJobAdvertResponse response = new CreateJobAdvertResponse();

        if (compRepo.existsById(request.getCompanyName())) {
            Company company = compRepo.findById(request.getCompanyName()).get();
            JobAdvert jobAdvert = new JobAdvert();
            JobAdvertControllerMethods.createJobAdvert(jobAdvert, company, jobRepo, compRepo, request, response);
        } else {
            response.setSuccess(false);
            response.setMessage("Company doesn't exist!");
        }

        return response;
    }


}
