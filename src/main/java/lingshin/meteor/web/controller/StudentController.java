package lingshin.meteor.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.service.StudentService;
import lombok.SneakyThrows;
import lingshin.meteor.web.result.WebException;
import lingshin.meteor.web.DTO.StudentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  @Autowired
  StudentService studentService;

  @GetMapping
  public List<StudentDTO> search(
      @ModelAttribute StudentDTO query) {
    return studentService.search(query);
  }

  @PostMapping
  public void update(
      @ModelAttribute StudentDTO query) {
    studentService.update(query);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public Student get(@PathVariable Integer id) {
    return studentService.getRepository().findById(id).orElseThrow(
        () -> new WebException("没有这个学生"));
  }
}
