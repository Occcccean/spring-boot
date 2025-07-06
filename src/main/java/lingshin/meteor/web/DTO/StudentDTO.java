package lingshin.meteor.web.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentDTO(
    Integer id,
    String name,
    String gender,
    String nation,
    LocalDateTime birthDate,
    String birthPlace,
    String idNumber,
    String studentId,
    String country,
    String homeAddress,
    String phoneNumber,
    String email,
    String dormId,
    Integer grade,
    String college,
    String major,
    String campus,
    LocalDate entranceDate,
    String classId,
    String wechat,
    String qq,
    String homePhoneNumber,
    String homeContactMan,
    Integer accountId,
    String mentor) {
}
