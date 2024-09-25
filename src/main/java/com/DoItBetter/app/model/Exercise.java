package com.DoItBetter.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exercises")
public class Exercise {

  @Id
  @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(name = "photo", nullable = false)
  private String photo;

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhoto() {
    return photo;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}
