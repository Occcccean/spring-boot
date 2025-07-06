package lingshin.meteor.web.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(length = 50)
  String name;

  @Column(length = 10)
  String gender;

  @Column(length = 50)
  String nation;

  @Column(name = "birth_date")
  LocalDateTime birthDate;

  @Column(name = "birth_place", length = 100)
  String birthPlace;

  @Column(name = "id_number", length = 20)
  String idNumber;

  @Column(name = "student_id", length = 20)
  String studentId;

  @Column(length = 50)
  String country;

  @Column(name = "home_address", length = 200)
  String homeAddress;

  @Column(name = "phone_number", length = 20)
  String phoneNumber;

  @Column(length = 50)
  String email;
  @Column(name = "dorm_id", length = 20)
  String dormId;

  Integer grade;

  @Column(length = 100)
  String college;
  @Column(length = 100)
  String major;
  @Column(length = 50)
  String campus;

  @Column(name = "entrance_date")
  LocalDate entranceDate;

  @Column(name = "class_id", length = 50)
  String classId;
  @Column(length = 50)
  String wechat;
  @Column(length = 20)
  String qq;
  @Column(name = "home_phone_number", length = 20)
  String homePhoneNumber;
  @Column(name = "home_contact_man", length = 50)
  String homeContactMan;
  @Column(name = "account_id", unique = true)
  Integer accountId;

  @ManyToOne
  @JoinColumn(name = "mentor_id", referencedColumnName = "id")
  Mentor mentor;
}
