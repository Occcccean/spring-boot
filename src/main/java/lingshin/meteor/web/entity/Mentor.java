package lingshin.meteor.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor implements Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(length = 50)
  String name;

  @Column(length = 100)
  String college;

  @OneToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  Account account;
}
