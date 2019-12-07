package me.schnaidt.business.model;

import lombok.Data;

@Data
public class SomeBusinessObject {

  private Long id;
  private String name;
  private String someValue;
  private String otherValue;
  private String created;

}
