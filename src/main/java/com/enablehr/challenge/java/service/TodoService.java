package com.enablehr.challenge.java.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enablehr.challenge.java.dto.TodoCreateRequest;
import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.dto.TodoUpdateRequest;
import com.enablehr.challenge.java.entity.Todo;
import com.enablehr.challenge.java.entity.TodoGroup;
import com.enablehr.challenge.java.exceptions.TodoNotFoundException;
import com.enablehr.challenge.java.repository.TodoGroupRepository;
import com.enablehr.challenge.java.repository.TodoRepository;

@Service
public class TodoService {
  
  private final TodoRepository todoRepository;
  private final TodoGroupRepository todoGroupRepository;

  public TodoService(TodoRepository todoRepository, TodoGroupRepository todoGroupRepository) {
    this.todoRepository = todoRepository;
    this.todoGroupRepository = todoGroupRepository;
  }
  
  public List<TodoResponse> getAllTodos() {
    return todoRepository.findAllUnclearedTodos();
  }

  @Transactional
  public TodoResponse create(TodoCreateRequest req) {
    Todo todo = req.toEntity();
    Todo savedTodo = todoRepository.save(todo);
    return TodoResponse.fromEntity(savedTodo);
  }

  @Transactional
  public TodoResponse createWithGroup(String group, TodoCreateRequest req) {
    TodoGroup todoGroup = todoGroupRepository
        .findByGroup(group)
        .orElseGet(() -> todoGroupRepository.save(new TodoGroup(group)));
    Todo todo = todoGroup.addTodo(req.toEntity());
    return TodoResponse.fromEntity(todo);
  }

  @Transactional
  public TodoResponse update(Integer id, TodoUpdateRequest req) {
    Todo todo = todoRepository
        .findById(id)
        .orElseThrow(() -> TodoNotFoundException.withId(id))
        .updateText(req.getText());
    return TodoResponse.fromEntity(todo);
  }

  @Transactional
  public void delete(Integer id) {
    todoRepository.deleteById(id);
  }

  @Transactional
  public TodoResponse updateCompleteTag(Integer id, boolean completed) {
    Todo todo = todoRepository
        .findById(id)
        .orElseThrow(() -> TodoNotFoundException.withId(id));
    todo.setCompleted(completed);
    return TodoResponse.fromEntity(todo);
  }

  @Transactional
  public List<TodoResponse> clearCompleted() {
    todoRepository.clearCompletedTodos();
    return todoRepository.findAllUnclearedTodos();
  }

  @Transactional
  public List<TodoResponse> completeAll() {
    todoRepository.completeAll();
    return todoRepository.findAllUnclearedTodos();
  }

  @Transactional
  public List<TodoResponse> uncompleteAll() {
    todoRepository.uncompleteAll();
    return todoRepository.findAllUnclearedTodos();
  }

}
