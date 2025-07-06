package lingshin.meteor.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lingshin.meteor.web.entity.Mentor;
import lingshin.meteor.web.entity.Student;
import lingshin.meteor.web.result.WebException;
import lingshin.meteor.web.service.MentorService;
import lingshin.meteor.web.service.StudentService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/mentor")
public class MentorController {
  @Autowired
  MentorService mentorService;

  @Autowired
  StudentService studentService;

  @GetMapping
  public List<Mentor> get(@RequestParam String name, @RequestParam String campus) {
    return mentorService.search(name, campus);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public Mentor get(@PathVariable Integer id) {
    return mentorService.getRepository().findById(id).orElseThrow(() -> new WebException("导师不存在"));
  }

  @SneakyThrows
  @GetMapping("/{id}/students")
  public List<Student> getStudents(@PathVariable Integer id) {
    return studentService.getRepository().findAllByMentor_Id(id);
  }
}
