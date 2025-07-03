package lingshin.meteor.web.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<Type> {
  Integer code;

  String msg;

  Type data;

  public static <Type> Result<Type> success(Type data) {
    return new Result<>(200, "ok", data);
  }

  public static <Type> Result<Type> fail(int code, String msg) {
    return new Result<>(code, msg, null);
  }

  public static <Type> Result<Type> fail(String msg) {
    return fail(400, msg);
  }
}
