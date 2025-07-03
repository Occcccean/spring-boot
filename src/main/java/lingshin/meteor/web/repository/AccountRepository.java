package lingshin.meteor.web.repository;

import lingshin.meteor.web.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Optional<Account> findByUsername(String username);

  @Modifying
  @Query("Update account set failed_times = failed_times + 1 where id = :id")
  void increateFailedTimesById(@Param("id") Integer id);

  @Modifying
  @Query("Update account set failed_times = 0 where id = :id")
  void clearFailedTimesById(@Param("id") Integer id);
}
