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
  public Exercise() {

  }

  public Exercise(Long id, String name, String description, String photo, String video) {
    this.id = id;
    this.name = name;
    this.photo = photo;
    this.description = description;
    this.video = video;
  }

  @Id
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(name = "photo", nullable = false)
  private String photo;

  @Column(name = "video", nullable = false)
  private String video;

  public void setVideo(String video) {
    this.video = video;
  }

  public String getVideo() {
    return video;
  }

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
