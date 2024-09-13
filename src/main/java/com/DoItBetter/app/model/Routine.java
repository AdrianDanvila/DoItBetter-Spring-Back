package com.DoItBetter.app.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rotuines")
public class Routine {

  public Routine() {

  }

  public Routine(String name, String description, User user) {
    this.name = name;
    this.description = description;
    this.user = user;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  @ColumnDefault("true")
  private boolean published = true;

  @Column(nullable = true)
  private List<String> exercises;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public void setUser(User user) {
    this.user = user;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setExercises(List<String> exercises) {
    this.exercises = exercises;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }

  public List<String> getExercises() {
    return exercises;
  }

  public String getDescription() {
    return description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public boolean isPublished() {
    return published;
  }

}
