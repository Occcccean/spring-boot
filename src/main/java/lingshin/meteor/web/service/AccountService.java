package lingshin.meteor.web.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lingshin.meteor.web.entity.Account;
import lingshin.meteor.web.repository.AccountRepository;
import lingshin.meteor.web.result.WebException;
import lingshin.meteor.web.util.Password;
import lombok.SneakyThrows;

@Service
public class AccountService {
  @Autowired
  AccountRepository accountRepository;

  public AccountRepository getRepository() {
    return accountRepository;
  }

  @SneakyThrows
  public Account login(String username, String password) {
    var maybeAccount = accountRepository.findByUsername(username);

    if (!maybeAccount.isPresent())
      throw new WebException("不存在的账号");

    var account = maybeAccount.get();
    var now = LocalDateTime.now();

    var lockTime = account.getLock_time();
    if (lockTime != null && Duration.between(
        lockTime, now).toMinutes() > 5)
      throw new WebException("还没到时间");

    var lastTime = account.getLast_password_change_date();
    if (lastTime != null && Duration.between(
        lastTime, now).toDays() > 90)
      throw new WebException("太久没改密码了");

    if (account.getFailed_times() > 5) {
      accountRepository.clearFailedTimesById(account.getId());
      throw new WebException("试太多次了");
    }

    var encodedPassword = account.getPassword();

    if (!new Password(password).verify(encodedPassword)) {
      accountRepository.increateFailedTimesById(account.getId());
      throw new WebException("密码有问题");
    }

    return account;
  }
}
