package lingshin.meteor.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lingshin.meteor.web.entity.Account;
import lingshin.meteor.web.result.WebException;
import lingshin.meteor.web.service.AccountService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired
  AccountService accountService;

  @GetMapping()
  public List<Account> getAll() {
    return accountService.getRepository().findAll();
  }

  @PostMapping()
  public void add(@RequestBody Account account) {
    accountService.getRepository().save(account);
  }

  @SneakyThrows
  @GetMapping("/{id}")
  public Account get(@PathVariable Integer id) {
    return accountService.getRepository().findById(id).orElseThrow(
        () -> new WebException("没有这个账号"));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    accountService.getRepository().deleteById(id);
  }

}
