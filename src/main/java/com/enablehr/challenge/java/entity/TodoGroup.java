package com.enablehr.challenge.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "todo_groups")
public class TodoGroup extends AbstractPersistable<Integer> {

  @Column(nullable = false)
  private String groupName;

  @OneToMany(mappedBy = "todoGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Todo> todos = new ArrayList<>();

  public Todo addTodo(Todo todo) {
    todos.add(todo);
    todo.setTodoGroup(this);
    return todo;
  }

  // constructors, getters, setters
  public TodoGroup() { }

  public TodoGroup(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  public List<Todo> getTodos() {
    return todos;
  }
  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }
}
