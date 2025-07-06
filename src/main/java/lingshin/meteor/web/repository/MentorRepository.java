package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Mentor;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends RoleRepository<Mentor> {
  List<Mentor> findAllByCollege(String campus);
}
