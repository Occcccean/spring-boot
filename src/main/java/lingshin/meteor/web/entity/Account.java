package lingshin.meteor.web.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(length = 50, unique = true)
  String username;

  @Column(length = 100)
  String password;

  @Column(length = 20)
  String role;

  Integer failed_times;

  Timestamp lock_time;

  Timestamp last_password_change_date;
}
