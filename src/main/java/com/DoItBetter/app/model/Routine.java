package com.DoItBetter.app.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
  private boolean published = false;

  @Column(nullable = true)
  @ElementCollection
  @CollectionTable(name = "routine_exercises", joinColumns = @JoinColumn(name = "routine_id"))
  private List<RoutineExercise> exercises;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public Routine setUser(User user) {
    this.user = user;
    return this;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Routine setName(String name) {
    this.name = name;
    return this;

  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Routine setExercises(List<RoutineExercise> exercises) {
    this.exercises = exercises;
    return this;

  }

  public Routine setDescription(String description) {
    this.description = description;
    return this;
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

  public List<RoutineExercise> getExercises() {
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

  public void addExercise(RoutineExercise exercise) throws Exception {
    List<RoutineExercise> routineExercises = new ArrayList<>(this.getExercises());
    for (RoutineExercise routineExercise : routineExercises) {
      if (routineExercise.getExercise().getId() == exercise.getExercise().getId()) {
        throw new Exception("Exception message");
      }
    }
    this.getExercises().add(exercise);
  }

  public void deleteExercise(RoutineExercise exercise) {
    List<RoutineExercise> routineExercises = new ArrayList<>(this.getExercises());
    for (RoutineExercise routineExercise : routineExercises) {
      if (routineExercise.getExercise().getId() == exercise.getExercise().getId()) {
        this.getExercises().remove(routineExercise);
      }
    }
  }

}
