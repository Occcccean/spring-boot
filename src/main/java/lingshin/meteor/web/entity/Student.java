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

  LocalDateTime birth_date;

  @Column(length = 100)
  String birth_place;

  @Column(length = 20)
  String id_number;

  @Column(length = 20)
  String student_id;

  @Column(length = 50)
  String country;

  @Column(length = 200)
  String home_address;

  @Column(length = 20)
  String phone_number;

  @Column(length = 50)
  String email;
  @Column(length = 20)
  String dorm_id;

  Integer grade;

  @Column(length = 100)
  String college;
  @Column(length = 100)
  String major;
  @Column(length = 50)
  String campus;

  LocalDate entrance_date;

  @Column(length = 50)
  String class_id;
  @Column(length = 50)
  String wechat;
  @Column(length = 20)
  String qq;
  @Column(length = 20)
  String home_phone_number;
  @Column(length = 50)
  String home_contact_man;
  @Column(unique = true)
  Integer account_id;

  @ManyToOne
  @JoinColumn(name = "mentor_id", referencedColumnName = "id")
  Mentor mentor;
}
