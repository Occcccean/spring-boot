package lingshin.meteor.web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "secretary")
public class Secretary implements CollegeRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 100)
  private String college;

  @OneToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true)
  private Account account;
}
