package me.schnaidt.business.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SomeBusinessObject {

  private Long id;
  private String name;
  private String someValue;
  private String otherValue;
  private LocalDateTime created;

}
