package lingshin.meteor.web.service;

import java.time.Duration;
import java.time.LocalDateTime;

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

  public boolean changePassword() {
    return false;
  }

  @SneakyThrows
  public Account login(String username, String password) {
    var maybeAccount = accountRepository.findByUsername(username);

    if (!maybeAccount.isPresent())
      throw new WebException("不存在的账号");

    var account = maybeAccount.get();
    var now = LocalDateTime.now();

    var lockTime = account.getLock_time();
    if (lockTime != null) {
      if (Duration.between(
          lockTime, now).toMinutes() < 5)
        throw new WebException("还没到时间");
      else {
        account.setLock_time(null);
        accountRepository.save(account);
      }
    }
    if (account.getFailed_times() > 5) {
      account.setFailed_times(0);
      account.setLock_time(now);
      accountRepository.save(account);
      throw new WebException("试太多次了");
    }

    var lastTime = account.getLast_password_change_date();
    if (lastTime != null && Duration.between(
        lastTime, now).toDays() > 90)
      throw new WebException("太久没改密码了");

    var encodedPassword = account.getPassword();

    if (!new Password(password).verify(encodedPassword)) {
      account.setFailed_times(account.getFailed_times() + 1);
      accountRepository.save(account);
      throw new WebException("密码有问题");
    }

    return account;
  }

  @SneakyThrows
  public void password(Integer id, String password) {
    var passwd = new Password(password);
    if (!passwd.isValidate())
      throw new WebException("密码格式有问题");

    var account = accountRepository.findById(id).orElseThrow(() -> new WebException("用户不存在"));
    account.setPassword(passwd.toEncodedPassword());

    accountRepository.save(account);
  }
}
