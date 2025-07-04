package lingshin.meteor.web.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebException extends Exception {
  Integer code;
  String message;

  public WebException(String msg) {
    this(400, msg);
  }

  @Override
  public String getMessage() {
    return message;
  }
}
