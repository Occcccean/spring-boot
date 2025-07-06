package lingshin.meteor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lingshin.meteor.web.entity.Account;
import lingshin.meteor.web.service.AccountService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  AccountService accountService;

  record UserDTO(String username, String password) {
  }

  record AccountDTO(
      Integer id,
      String username,
      String name) {
    static AccountDTO fromAccount(Account account) {
      return new AccountDTO(account.getId(), account.getUsername(), "");
    }
  }

  @PostMapping("/login")
  public AccountDTO login(@RequestBody UserDTO user) {
    return AccountDTO.fromAccount(
        accountService.login(user.username, user.password));
  }

  record UserPasswordDTO(Integer id, String password) {
  }

  @PostMapping("/chpasswd")
  public void changePassword(@RequestBody UserPasswordDTO user) {
    accountService.password(
        user.id,
        user.password);
  }
}
