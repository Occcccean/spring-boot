package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Optional<Account> findByUsername(String username);
}
