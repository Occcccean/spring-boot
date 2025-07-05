package lingshin.meteor.web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "university_leader")
public class UniversityLeader implements Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, nullable = false)
  private String name;

  @OneToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true)
  private Account account;
}
