package lingshin.meteor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lingshin.meteor.web.result.Result;
import lingshin.meteor.web.util.Password;

@RestController
@RequestMapping("/test")
class Hello {
  @GetMapping("/hello")
  public Result<String> hello() {
    return Result.ok("hello world!");
  }

  @GetMapping("/password")
  public Result<String> encode(@RequestParam String password) {
    return Result.ok(new Password(password).getEncodedPassword());
  }
}
