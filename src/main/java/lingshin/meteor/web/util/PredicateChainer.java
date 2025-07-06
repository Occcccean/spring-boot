package lingshin.meteor.web.util;

import java.util.function.Predicate;
import java.util.function.Function;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PredicateChainer<Type> {
  Predicate<Type> predicate;

  public static <Type> PredicateChainer<Type> ofField(String value, Function<Type, String> getField) {
    return of(value, new Predicate<Type>() {
      public boolean test(Type it) {
        var field = getField.apply(it);
        return field != null && field.equals(value);
      }
    });
  }

  public static <Type> PredicateChainer<Type> of(String value, Predicate<Type> newPredicate) {
    return new PredicateChainer<>(value == null || value.isEmpty() ? x -> true : newPredicate);
  }

  public static <Type> PredicateChainer<Type> of(Object value, Predicate<Type> newPredicate) {
    return new PredicateChainer<>(value == null ? x -> true : newPredicate);
  }

  public static <Type, Value> PredicateChainer<Type> of(Value value, Predicate<Value> isUsable,
      Predicate<Type> newPredicate) {
    return new PredicateChainer<>(isUsable.test(value) ? x -> true : newPredicate);
  }

  public PredicateChainer<Type> mapChain(String value, Function<Type, String> getField) {
    return chain(value, new Predicate<Type>() {
      public boolean test(Type it) {
        var field = getField.apply(it);
        return field != null && field.equals(value);
      }
    });
  }

  public PredicateChainer<Type> chain(String value, Predicate<Type> otherPredicate) {
    return chain(value, it -> it != null && !value.isEmpty(), otherPredicate);
  }

  public PredicateChainer<Type> chain(Object value, Predicate<Type> otherPredicate) {
    return chain(value, it -> it != null, otherPredicate);
  }

  public <Value> PredicateChainer<Type> chain(Value value, Predicate<Value> isUsable, Predicate<Type> otherPredicate) {
    return new PredicateChainer<Type>(
        isUsable.test(value) ? predicate.and(otherPredicate) : predicate);
  }

  public Predicate<Type> toPredicate() {
    return predicate;
  }
}
