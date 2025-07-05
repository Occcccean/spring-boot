package lingshin.meteor.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository<Type> extends JpaRepository<Type, Integer> {
  Optional<Type> findByAccountId(Integer id);
}
