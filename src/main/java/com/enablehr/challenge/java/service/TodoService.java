package com.enablehr.challenge.java.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enablehr.challenge.java.dto.TodoCreateRequest;
import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.dto.TodoUpdateRequest;
import com.enablehr.challenge.java.entity.Todo;
import com.enablehr.challenge.java.exceptions.TodoNotFoundException;
import com.enablehr.challenge.java.repository.TodoRepository;

@Service
public class TodoService {
  
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  
  public List<TodoResponse> getAllTodos() {
    return todoRepository.findAllUnclearedTodos();
  }

  @Transactional
  public TodoResponse create(TodoCreateRequest request) {
    Todo todo = request.toEntity();
    Todo savedTodo = todoRepository.save(todo);
    return TodoResponse.fromEntity(savedTodo);
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
  public List<TodoResponse> toggleComplete() {
    todoRepository.toggleComplete();
    return todoRepository.findAllUnclearedTodos();
  }

}
