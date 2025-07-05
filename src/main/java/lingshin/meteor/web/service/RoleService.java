package lingshin.meteor.web.service;

import lingshin.meteor.web.entity.Role;
import lingshin.meteor.web.repository.AccountRepository;
import lingshin.meteor.web.repository.CollegeLeaderRepository;
import lingshin.meteor.web.repository.MentorRepository;
import lingshin.meteor.web.repository.SecretaryRepository;
import lingshin.meteor.web.repository.StudentLeaderRepository;
import lingshin.meteor.web.repository.StudentRepository;
import lingshin.meteor.web.repository.UniversityLeaderRepository;
import lingshin.meteor.web.result.WebException;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  @Autowired
  AccountRepository accountRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  MentorRepository mentorRepository;

  @Autowired
  SecretaryRepository secretaryRepository;

  @Autowired
  CollegeLeaderRepository collegeLeaderRepository;

  @Autowired
  StudentLeaderRepository studentLeaderRepository;

  @Autowired
  UniversityLeaderRepository universityLeaderRepository;

  @SneakyThrows
  public Role getRole(Integer id) {
    var account = accountRepository.findById(id).orElseThrow(() -> new WebException("不存在的账号"));
    return (switch (account.getRole()) {
      case "student" -> studentRepository;
      case "mentor" -> mentorRepository;
      case "secretary" -> secretaryRepository;
      case "college_leader" -> collegeLeaderRepository;
      case "student_leader" -> studentLeaderRepository;
      case "university_leader" -> universityLeaderRepository;
      default -> throw new WebException("不存在的角色");
    }).findByAccountId(id).orElseThrow(() -> new WebException("找不到角色"));
  }
}
