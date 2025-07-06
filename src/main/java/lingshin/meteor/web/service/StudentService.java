package lingshin.meteor.web.service;

import lingshin.meteor.web.repository.StudentRepository;
import lingshin.meteor.web.DTO.StudentDTO;
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

  public List<StudentDTO> search(
      StudentDTO student) {
    var search_condition = PredicateChainer
        .<Student>ofField(student.studentId(), Student::getStudentId)
        .mapChain(student.major(), Student::getMajor)
        .mapChain(student.college(), Student::getCollege)
        .mapChain(student.campus(), Student::getCampus)
        .mapChain(student.gender(), Student::getGender)
        .mapChain(student.country(), Student::getCountry)
        .mapChain(student.nation(), Student::getNation)
        .chain(student.name(), stu -> stu.getName().contains(student.name()))
        .chain(student.mentor(), stu -> Optional
            .ofNullable(stu.getMentor())
            .map(m -> m.getName().contains(student.mentor()))
            .orElse(false))
        .toPredicate();
    return studentRepository
        .findAll().stream()
        .filter(search_condition)
        .map(stu -> student) // FIXME: bad code here
        .collect(Collectors.toList());
  }

  public void update(StudentDTO studentdDTO) {
  }
}
