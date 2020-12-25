package com.ok.dao.controllers;

import com.ok.ValidationChecks;
import com.ok.dao.JobAdvertRepository;
import com.ok.dao.UserCertificationInformationRepository;
import com.ok.dao.UserJobExperienceRepository;
import com.ok.dao.UserRepository;
import com.ok.dao.controllers.methods.jobadvert.JobAdvertControllerMethods;
import com.ok.dao.controllers.methods.user.UserControllerMethods;
import com.ok.dao.entities.*;
import com.ok.dao.requests.user.UserLoginRequest;
import com.ok.dao.requests.user.*;
import com.ok.dao.responses.user.UserLoginResponse;
import com.ok.dao.responses.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    JobAdvertRepository jobRepo;

    @Autowired
    UserCertificationInformationRepository certRepo;

    @Autowired
    UserJobExperienceRepository userJobRepo;

    @GetMapping("/find-by-mail/{userMail}")
    public List<User> findUserByUserMail(@PathVariable String userMail) {
        return userRepo.findByUserMail(userMail);
    }

    @GetMapping("/find-by-first-name/{firstName}")
    public List<User> findUserByFirstName(@PathVariable String firstName) {
        return userRepo.findByFirstName(firstName);
    }

    @GetMapping("/find-by-last-name/{lastName}")
    public List<User> findUserByLastName(@PathVariable String lastName) {
        return userRepo.findByLastName(lastName);
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

    @PostMapping("/apply-job")
    public UserApplyJobResponse userApplyJob(@RequestBody UserApplyJobRequest request) {
        UserApplyJobResponse response = new UserApplyJobResponse();
        User registerUser = new User();
        JobAdvert registerJobAdvert = new JobAdvert();
        if (userRepo.existsById(request.getUserMail())) {
            if (jobRepo.existsById(request.getCompanyNameBranchPosition())) {
                User user = userRepo.findById(request.getUserMail()).get();
                JobAdvert jobAdvert = jobRepo.findById(request.getCompanyNameBranchPosition()).get();
                UserControllerMethods.userApplyJob(registerUser, user, jobAdvert, registerJobAdvert, userRepo, jobRepo, response);
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

    @PostMapping("/change-id")
    public UserChangeIdResponse userChangeId(@RequestBody UserChangeIdRequest request) {
        UserChangeIdResponse response = new UserChangeIdResponse();

        if (userRepo.existsById(request.getUserMail())) {
            if (userRepo.existsById(request.getNewUserMail())) {
                User user = userRepo.findById(request.getUserMail()).get();
                if (user.getUserPassword().equals(request.getUserPassword())) {
                    user.setUserMail(request.getNewUserMail());
                    userRepo.save(user);
                    response.setSuccess(true);
                    response.setMessage("Successful!");
                } else {
                    response.setSuccess(false);
                    response.setMessage("Wrong password!");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("User mail already exist!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }


    @PostMapping("/update")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            user.setAddress(request.getAddress());
            user.setBirthday(request.getBirthday());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            for (int i = 0; i < user.getAppliedJobs().size(); i++) {
                JobAdvert findJobAdvert = jobRepo.findById(user.getAppliedJobs().get(i).getCompanyNameBranchPosition()).get();
                int userIndex = JobAdvertControllerMethods.findUserInApplicants(user.getUserMail(), findJobAdvert.getApplicants());
                findJobAdvert.getApplicants().remove(userIndex);
                findJobAdvert.getApplicants().add(user);
                jobRepo.save(findJobAdvert);
            }
            userRepo.save(user);
            response.setSuccess(true);
            response.setMessage("Successful!");
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }


    @PostMapping("/follow/job-advert")
    public UserFollowJobAdvertResponse userFollowJobAdvertResponse(@RequestBody UserFollowJobAdvertRequest request) {
        UserFollowJobAdvertResponse response = new UserFollowJobAdvertResponse();

        if (jobRepo.existsById(request.getCompanyNameBranchPosition())) {
            if (userRepo.existsById(request.getUserMail())) {
                User user = userRepo.findById(request.getUserMail()).get();
                JobAdvert jobAdvert = jobRepo.findById(request.getCompanyNameBranchPosition()).get();
                JobAdvert registerJobAdvert = new JobAdvert();
                UserControllerMethods.userFollowingJobAdvert(user, jobAdvert, registerJobAdvert, userRepo, response);
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

    @PostMapping("/delete-talent")
    public UserDeleteTalentResponse userDeleteTalent(@RequestBody UserDeleteTalentRequest request) {
        UserDeleteTalentResponse response = new UserDeleteTalentResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();

            int talentIndex = UserControllerMethods.findTalent(user.getTalents(), request.getTalent());

            if (talentIndex != -1)
                user.getTalents().remove(talentIndex);

            userRepo.save(user);
            response.setSuccess(true);
            response.setMessage("Successful!");
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/add-talent")
    public AddUserTalentResponse addUserTalent(@RequestBody AddUserTalentRequest request) {
        AddUserTalentResponse response = new AddUserTalentResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            user.getTalents().add(request.getTalent());
            userRepo.save(user);
            response.setSuccess(true);
            response.setMessage("Successful!");
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }

        return response;
    }

    @PostMapping("/change-password")
    public ChangeUserPasswordResponse changeCompanyPassword(@RequestBody ChangeUserPasswordRequest request) {
        ChangeUserPasswordResponse response = new ChangeUserPasswordResponse();

        if (userRepo.existsById(request.getEmail())) {
            User user = userRepo.findById(request.getNewPass()).get();
            if (request.getNewPass().equals(request.getNewPassAgain())) {
                user.setUserPassword(request.getNewPass());
                userRepo.save(user);
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Password mismatch!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("add-language")
    public AddUserLanguageInformationResponse addUserLanguageInformation(@RequestBody AddUserLanguageInformationRequest request) {
        AddUserLanguageInformationResponse response = new AddUserLanguageInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            if (request.getLevel() < 0 || request.getLevel() > 5) {
                response.setSuccess(false);
                response.setMessage("Level must be between 0-5");
            } else {
                user.getUserLanguageInformation().getLanguages().put(request.getLanguage(), request.getLevel());
                response.setSuccess(true);
                response.setMessage("Successful!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }

        return response;
    }


    @PostMapping("/update/education-information")
    public UpdateUserEducationInformationResponse updateUserEducationInformation(@RequestBody UpdateUserEducationInformationRequest request) {
        UpdateUserEducationInformationResponse response = new UpdateUserEducationInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();

            UserEducationInformation educationInformation = new UserEducationInformation(request.getCollege(), request.getFaculty(),
                    request.getDepartment(), request.getStartDate(), request.getEndDate(), request.getEducationLanguage(), request.getGpa());


            user.setUserEducationInformation(educationInformation);

            userRepo.save(user);
            response.setSuccess(true);
            response.setMessage("Successful!");
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/update/general-information")
    public UpdateUserGeneralInformationResponse updateUserEducationInformation(@RequestBody UpdateUserGeneralInformationRequest request) {
        UpdateUserGeneralInformationResponse response = new UpdateUserGeneralInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            UserGeneralInformation userGeneralInformation = new UserGeneralInformation(request.getNationality(), request.getWageExpectation(),
                    request.getDriverLicence(), request.getGender(), request.getSummary());
            user.setUserGeneralInformation(userGeneralInformation);

            userRepo.save(user);
            response.setSuccess(true);
            response.setMessage("Successful!");
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/delete/certification")
    public DeleteUserCertificationResponse deleteUserCertification(@RequestBody DeleteUserCertificationRequest request) {
        DeleteUserCertificationResponse response = new DeleteUserCertificationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            if (certRepo.existsById(request.getCertificationId())) {
                User user = userRepo.findById(request.getUserMail()).get();
                certRepo.deleteById(request.getCertificationId());

                int certificationIndex = UserControllerMethods.findUserCertification(user.getUserCertificationsInformation(),
                        request.getCertificationId());

                if (certificationIndex != -1) {
                    user.getUserCertificationsInformation().remove(certificationIndex);
                }

                userRepo.save(user);
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Certification doesn't exist!");
            }


        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/update/certification-information")
    public UpdateUserCertificationResponse updateUserCertification(@RequestBody UpdateUserCertificationRequest request) {
        UpdateUserCertificationResponse response = new UpdateUserCertificationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            if (certRepo.existsById(request.getCertificationId())) {
                User user = userRepo.findById(request.getUserMail()).get();
                certRepo.deleteById(request.getCertificationId());

                int certificationIndex = UserControllerMethods.findUserCertification(user.getUserCertificationsInformation(),
                        request.getCertificationId());

                UserCertificationInformation registerUserCertification = new UserCertificationInformation(request.getCertificationName(),
                        request.getUserMail(), request.getInstitution(), request.getCertificationDate());
                if (certificationIndex != -1) {
                    user.getUserCertificationsInformation().remove(certificationIndex);
                }
                user.getUserCertificationsInformation().add(registerUserCertification);

                userRepo.save(user);
                certRepo.save(registerUserCertification);
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Certification doesn't exist!");
            }

        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("add-education-information")
    public AddUserEducationInformationResponse addUserEducationInformation(@RequestBody AddUserEducationInformationRequest request) {
        AddUserEducationInformationResponse response = new AddUserEducationInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();

            UserControllerMethods.addUserEducationInformation(user, userRepo, request, response);

        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }

        return response;
    }

    @PostMapping("/add-job-experience")
    public AddUserJobExperienceResponse addUserJobExperience(@RequestBody AddUserJobExperienceRequest request) {
        AddUserJobExperienceResponse response = new AddUserJobExperienceResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            UserControllerMethods.addUserJobExperience(user, userRepo, userJobRepo, request, response);
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }

        return response;
    }

    @PostMapping("/add-information")
    public AddUserGeneralInformationResponse addUserGeneralInformation(@RequestBody AddUserGeneralInformationRequest request) {
        AddUserGeneralInformationResponse response = new AddUserGeneralInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();
            UserControllerMethods.addUserGeneralInformation(user, userRepo, request, response);
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/add-certification")
    public AddCertificationInformationResponse addUserCertificationInformation(@RequestBody AddCertificationInformationRequest request) {
        AddCertificationInformationResponse response = new AddCertificationInformationResponse();

        if (userRepo.existsById(request.getUserMail())) {
            User user = userRepo.findById(request.getUserMail()).get();

            UserControllerMethods.addUserCertification(user, userRepo, certRepo, request, response);

        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;

    }

    @PostMapping("/login")
    public UserLoginResponse userLogin(@RequestBody UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        if (userRepo.existsById(request.getMail())) {
            User user = userRepo.findById(request.getMail()).get();
            if (BCrypt.checkpw(request.getPassword(), user.getUserPassword())) {
                response.setSuccess(true);
                response.setMessage("Successful!");
            } else {
                response.setSuccess(false);
                response.setMessage("Wrong password!");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("User doesn't exist!");
        }
        return response;
    }

    @PostMapping("/create")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        if (!userRepo.existsById(request.getUserMail())) {
            if (ValidationChecks.isValidEmailAddress(request.getUserMail())) {
                if (request.getUserPassword().equals(request.getUserPasswordAgain())) {
                    User user = new User();

                    UserControllerMethods.createUser(user, userRepo, request, response);
                } else {
                    response.setSuccess(false);
                    response.setMessage("Password mismatch!");
                }

            } else {
                response.setSuccess(false);
                response.setMessage("Invalid email address!");
            }

        } else {
            response.setSuccess(false);
            response.setMessage("User mail already exist!");
        }
        return response;
    }


}
