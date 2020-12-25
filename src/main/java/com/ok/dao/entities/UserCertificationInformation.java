package com.ok.dao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class UserCertificationInformation {


    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    @Id
    @org.springframework.data.annotation.Id
    private int certificationId;

    private String userMail;
    private String certificationName;
    private String institution;
    private String certificationDate;

    public UserCertificationInformation(String certificationName, String userMail, String institution, String certificationDate) {
        certificationId = ID_GENERATOR.getAndIncrement();
        this.certificationName = certificationName;
        this.userMail = userMail;
        this.institution = institution;
        this.certificationDate = certificationDate;
    }


    public int getCertificationId() {
        return certificationId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(String certificationDate) {
        this.certificationDate = certificationDate;
    }

}
