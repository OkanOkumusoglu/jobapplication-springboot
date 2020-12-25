package com.ok;

import com.ok.dao.JobAdvertRepository;
import com.ok.dao.entities.JobAdvert;
import com.ok.dao.entities.User;
import com.ok.dao.entities.UserCertificationInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobApplication {


    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);



    }


}
