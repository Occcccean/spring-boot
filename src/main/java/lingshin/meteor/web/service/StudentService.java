package lingshin.meteor.web.service;

import lingshin.meteor.web.repository.StudentRepository;
import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.util.PredicateChainer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  @Autowired
  StudentRepository studentRepository;

  public StudentRepository getRepository() {
    return studentRepository;
  }

  public List<Student> search(
      String name,
      String sid,
      String major,
      String college,
      String campus,
      String gender,
      String nation,
      String country,
      String mentorName) {
    var search_condition = PredicateChainer
        .<Student>of(sid)
        .chain(major)
        .chain(college)
        .chain(campus)
        .chain(gender)
        .chain(country)
        .chain(nation)
        .chain(name, stu -> stu.getName().contains(name))
        .chain(mentorName, stu -> stu.getMentor().getName().contains(mentorName))
        .toPredicate();
    return studentRepository
        .findAll().stream()
        .filter(search_condition)
        .collect(Collectors.toList());
  }
}
