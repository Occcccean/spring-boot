package lingshin.meteor.web.service;

import lingshin.meteor.web.repository.StudentRepository;
import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.util.PredicateChainer;

import java.util.List;
import java.util.Optional;
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
        .<Student>ofField(sid, Student::getStudent_id)
        .mapChain(major, Student::getMajor)
        .mapChain(college, Student::getCollege)
        .mapChain(campus, Student::getCampus)
        .mapChain(gender, Student::getGender)
        .mapChain(country, Student::getCountry)
        .mapChain(nation, Student::getNation)
        .chain(name, stu -> stu.getName().contains(name))
        .chain(mentorName, stu -> Optional
            .ofNullable(stu.getMentor())
            .map(m -> m.getName().contains(mentorName))
            .orElse(false))
        .toPredicate();
    return studentRepository
        .findAll().stream()
        .filter(search_condition)
        .collect(Collectors.toList());
  }
}
