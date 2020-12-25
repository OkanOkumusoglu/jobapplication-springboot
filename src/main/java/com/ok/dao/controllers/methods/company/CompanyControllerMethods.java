package com.ok.dao.controllers.methods.company;

import com.ok.dao.CompanyRepository;
import com.ok.dao.entities.Company;
import com.ok.dao.requests.company.ChangeCompanyPasswordRequest;
import com.ok.dao.requests.company.CreateCompanyRequest;
import com.ok.dao.requests.company.UpdateCompanyRequest;
import com.ok.dao.responses.company.ChangeCompanyPasswordResponse;
import com.ok.dao.responses.company.CreateCompanyResponse;
import com.ok.dao.responses.company.UpdateCompanyResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CompanyControllerMethods {


    public static void changeCompanyPassword(Company company, CompanyRepository compRepo, ChangeCompanyPasswordRequest request,
                                             ChangeCompanyPasswordResponse response) {
        company.setCompanyPassword(request.getNewPass());
        compRepo.save(company);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void createCompany(Company company, CompanyRepository compRepo, CreateCompanyRequest request, CreateCompanyResponse response) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12

        company.setCompanyName(request.getCompanyName());
        company.setCompanyMail(request.getCompanyMail());

        String encodedPassword = encoder.encode(request.getCompanyPassword());
        company.setCompanyPassword(encodedPassword);

        company.setCompanySector(request.getCompanySector());
        company.setEmployeeCount(request.getEmployeeCount());
        company.setFoundationYear(request.getFoundationYear());
        company.setWebsite(request.getWebsite());
        compRepo.save(company);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }

    public static void updateCompany(Company company, CompanyRepository compRepo ,UpdateCompanyRequest request, UpdateCompanyResponse response){
        company.setCompanyMail(request.getCompanyMail());
        company.setCompanySector(request.getCompanySector());
        company.setEmployeeCount(request.getEmployeeCount());
        company.setFoundationYear(request.getFoundationYear());
        company.setWebsite(request.getWebsite());

        compRepo.save(company);
        response.setSuccess(true);
        response.setMessage("Successful!");
    }


}
