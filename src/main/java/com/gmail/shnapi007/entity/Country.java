package com.gmail.shnapi007.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private Long id;

  //@ManyToOne()
  //@JoinColumn(nullable = false, name = "id")
  private String name;


  public Country() {
  }

  public Country(String name) {
    this.name = name;
  }
}
