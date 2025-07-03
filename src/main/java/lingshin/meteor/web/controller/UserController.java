package lingshin.meteor.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lingshin.meteor.web.entity.Account;
import lingshin.meteor.web.service.AccountService;

@RestController
public class UserController {
  @Autowired
  AccountService accountService;

  @PostMapping("/login")
  public Account login(@RequestBody Map<String, String> requestParams) {
    return accountService.login(
        requestParams.get("username"),
        requestParams.get("password"));
  }
}
