package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Student;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends RoleRepository<Student> {
}
