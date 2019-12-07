package me.schnaidt.business.persistence.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "BUSINESS_TABLE")
@Entity
@Data
public class BusinessEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", updatable = false, nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "SOME_VALUE")
  private String someValue;

  @Column(name = "OTHER_VALUE")
  private String otherValue;

  @Column(name = "CREATED", nullable = false)
  private LocalDateTime created;

}
