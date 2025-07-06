package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Student;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends RoleRepository<Student> {
  List<Student> findAllByMentor_Id(Integer id);
}
