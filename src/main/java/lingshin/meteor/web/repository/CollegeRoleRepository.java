package lingshin.meteor.web.repository;

import java.util.List;

public interface CollegeRoleRepository<Type> extends RoleRepository<Type> {
  List<Type> findAllByCollege(String college);
}
