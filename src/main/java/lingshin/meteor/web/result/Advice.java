package lingshin.meteor.web.result;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.SneakyThrows;

@RestControllerAdvice
public class Advice implements ResponseBodyAdvice<Object> {

  @SneakyThrows
  @Override
  public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType mediaType,
      Class converter, ServerHttpRequest request, ServerHttpResponse response) {
    if (object instanceof Result)
      return object;
    return Result.success(object);

  }

  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @ExceptionHandler(WebException.class)
  public Result<Object> exception(WebException err) {
    return Result.fail(err.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public Result<Object> exception(Exception err) {
    return Result.fail(500, err.getMessage());
  }
}
