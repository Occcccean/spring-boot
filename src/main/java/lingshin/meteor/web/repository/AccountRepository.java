package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findByUsername(String username);
}
