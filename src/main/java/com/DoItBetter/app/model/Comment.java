package com.DoItBetter.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Comment {

  public Comment(String content, long user_id) {
    this.content = content;
    this.user_id = user_id;
  }

  public Comment() {
    // TODO Auto-generated constructor stub
  }

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private long user_id;

  @Column(nullable = false)
  private String user_name;

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public long getUser_id() {
    return user_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_name() {
    return user_name;
  }
}
