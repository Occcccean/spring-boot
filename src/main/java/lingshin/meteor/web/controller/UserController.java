package lingshin.meteor.web.controller;

import java.util.Map;

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

  @PostMapping("/login")
  public Account login(@RequestBody UserDTO user) {
    return accountService.login(user.username, user.password);
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
