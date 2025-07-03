package lingshin.meteor.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lingshin.meteor.web.entity.Account;
import lingshin.meteor.web.repository.AccountRepository;

@Service
public class AccountService {
  @Autowired
  AccountRepository accountRepository;

  public AccountRepository getRepository() {
    return accountRepository;
  }

  public Account login(String username, String password) {
    var account = accountRepository.findByUsername(username);

    return account;
  }
}
