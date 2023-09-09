package com.enablehr.challenge.java.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo extends AbstractPersistable<Integer> {

  @Column(nullable = false)
  private boolean completed;

  @Column(nullable = false)
  private String text;

  private boolean cleared = false;

  public String getText() {
    return text;
  }
  public void setText(final String text) {
    this.text = text;
  }
  public boolean isCompleted() {
    return completed;
  }
  public void setCompleted(final boolean completed) {
    this.completed = completed;
  }
  public boolean isCleared() {
    return cleared;
  }
  public void setCleared(final boolean cleared) {
    this.cleared = cleared;
  }
}
