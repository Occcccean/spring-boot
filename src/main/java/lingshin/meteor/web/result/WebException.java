package lingshin.meteor.web.result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebException extends Exception {
  String msg;

  @Override
  public String getMessage() {
    return msg;
  }
}
