package lingshin.meteor.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{id}")
  public Account get(@PathVariable Integer id) {
    return accountService.getRepository().findById(id).orElse(null);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    accountService.getRepository().deleteById(id);
  }

}
