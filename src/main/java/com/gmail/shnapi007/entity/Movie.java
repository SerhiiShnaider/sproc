package com.gmail.shnapi007.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private Long id;


  private String name;

  private String originalName;

  private int year;

  private String duration;

  private String producer;

  private String description;

  @OneToMany(mappedBy = "movies")
  Set<Country> countries;

  public Movie() {
  }
}
