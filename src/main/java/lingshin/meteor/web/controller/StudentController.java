package lingshin.meteor.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.service.StudentService;
import lombok.SneakyThrows;
import lingshin.meteor.web.result.WebException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  @Autowired
  StudentService studentService;

  record StudentQueryDTO(
      String name,
      String sid,
      String major,
      String college,
      String campus,
      String gender,
      String nation,
      String country,
      String mentor) {
    static StudentQueryDTO fromStudent(Student student) {
      return new StudentQueryDTO(
          student.getName(),
          student.getStudentId(),
          student.getMajor(),
          student.getCollege(),
          student.getCampus(),
          student.getGender(),
          student.getNation(),
          student.getCountry(),
          student.getMentor().getId().toString());
    }
  }

  @GetMapping
  public List<StudentQueryDTO> search(
      @ModelAttribute StudentQueryDTO query) {
    return studentService.search(
        query.name,
        query.sid,
        query.major,
        query.college,
        query.campus,
        query.gender,
        query.nation,
        query.country,
        query.mentor)
        .stream()
        .map(StudentQueryDTO::fromStudent)
        .collect(Collectors.toList());
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public Student get(@PathVariable Integer id) {
    return studentService.getRepository().findById(id).orElseThrow(
        () -> new WebException("没有这个学生"));
  }
}
