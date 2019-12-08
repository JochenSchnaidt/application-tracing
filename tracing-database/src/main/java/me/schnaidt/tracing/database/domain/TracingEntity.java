package me.schnaidt.tracing.database.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "TRACE_TABLE")
@Entity
@Data
public class TracingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", updatable = false, nullable = false)
  private Long id;

  @Column(name = "REQUEST")
  private String request;

  @Column(name = "RESPONSE")
  private String response;

  @Column(name = "CREATED", nullable = false)
  private LocalDateTime created;

}
