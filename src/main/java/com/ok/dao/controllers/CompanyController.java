package com.ok.dao.controllers;

import com.ok.ValidationChecks;
import com.ok.dao.CompanyRepository;
import com.ok.dao.JobAdvertRepository;
import com.ok.dao.controllers.methods.company.CompanyControllerMethods;
import com.ok.dao.entities.Company;
import com.ok.dao.requests.company.*;
import com.ok.dao.responses.company.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyRepository compRepo;

    @Autowired
    JobAdvertRepository jobRepo;

    @GetMapping("/find-by-company-name/{companyName}")
    public List<Company> findCompaniesByCompanyName(@PathVariable String companyName) {
        return compRepo.findByCompanyName(companyName);
    }

    @GetMapping("/find-by-sector/{sector}")
    public List<Company> findCompaniesBySector(@PathVariable String sector) {
        return compRepo.findByCompanySector(sector);
    }

    @RequestMapping(value = "/image/{mail}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("mail") String mail) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("images/" + mail + ".jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @PostMapping("/update")
    public UpdateCompanyResponse updateCompany(@RequestBody UpdateCompanyRequest request) {
        UpdateCompanyResponse response = new UpdateCompanyResponse();

        if (compRepo.existsById(request.getCompanyName())) {
            Company company = compRepo.findById(request.getCompanyName()).get();

            CompanyControllerMethods.updateCompany(company, compRepo, request, response);
        } else {
            response.setSuccess(false);
            response.setMessage("Company doesn't exist!");
        }
        return response;
    }

    @PostMapping
    public ChangeCompanyIdResponse companyChangeId(@RequestBody ChangeCompanyIdRequest request) {
        ChangeCompanyIdResponse response = new ChangeCompanyIdResponse();

        if (compRepo.existsById(request.getCompanyName())) {
            if (compRepo.existsById(request.getNewCompanyName())) {
                Company company = compRepo.findById(request.getCompanyName()).get();
                if (company.getCompanyPassword().equals(request.getCompanyPassword())) {
                    company.setCompanyName(request.getNewCompanyName());
                    compRepo.save(company);
                    response.setSuccess(true);
                    response.setMessage("Successful!");
                } else {
                    response.setSuccess(false);
                    response.setMessage("Wrong password!");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("Company name already taken!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Company doesn't exist!");
        }
        return response;
    }

    @PostMapping("/change-password")
    public ChangeCompanyPasswordResponse changeCompanyPassword(@RequestBody ChangeCompanyPasswordRequest request) {
        ChangeCompanyPasswordResponse response = new ChangeCompanyPasswordResponse();

        if (compRepo.existsById(request.getCompanyName())) {
            Company company = compRepo.findById(request.getCompanyName()).get();
            if (request.getNewPass().equals(request.getNewPassAgain())) {
                CompanyControllerMethods.changeCompanyPassword(company, compRepo, request, response);
            } else {
                response.setSuccess(false);
                response.setMessage("Password mismatch!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Company doesn't exist!");
        }
        return response;
    }

    @PostMapping("/login")
    public CompanyLoginResponse companyLogin(@RequestBody CompanyLoginRequest request) {
        CompanyLoginResponse response = new CompanyLoginResponse();
        if (compRepo.existsById(request.getCompanyName())) {
            Company company = compRepo.findById(request.getCompanyName()).get();
            if (BCrypt.checkpw(request.getCompanyPassword(), company.getCompanyPassword())) {
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Wrong password!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Company doesn't exist!");
        }
        return response;
    }


    @PostMapping("/create")
    public CreateCompanyResponse createCompany(@RequestBody CreateCompanyRequest request) {
        CreateCompanyResponse response = new CreateCompanyResponse();

        if (!compRepo.existsById(request.getCompanyMail())) {
            if (ValidationChecks.isValidEmailAddress(request.getCompanyMail())) {
                if (request.getCompanyPassword().equals(request.getCompanyPasswordAgain())) {
                    Company company = new Company();
                    CompanyControllerMethods.createCompany(company, compRepo, request, response);
                } else {
                    response.setSuccess(false);
                    response.setMessage("Password mismatch!");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("Mail is invalid!");
            }


        } else {
            response.setSuccess(false);
            response.setMessage("Company mail is already taken!");
        }
        return response;
    }
}
