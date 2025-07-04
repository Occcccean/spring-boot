package lingshin.meteor.web.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Password {
  String password;

  public String getEncodedPassword() {
    return SM3Util.encrypt(password);
  }

  public boolean verify(String encoded) {
    return SM3Util.verify(password, encoded);
  }

  public boolean isValidate() {
    return password.matches(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct}).{8,}$");
  }
}
