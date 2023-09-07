package com.enablehr.challenge.java.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

  public TodoResponse create(TodoCreateRequest request) {
    Todo todo = request.toEntity();
    Todo savedTodo = todoRepository.save(todo);
    return TodoResponse.fromEntity(savedTodo);
  }

  public TodoResponse update(TodoUpdateRequest req) {
    Todo todo = todoRepository
        .findById(req.getId())
        .orElseThrow(() -> TodoNotFoundException.withId(req.getId()));
    todo.setText(req.getUpdatedText());
    return TodoResponse.fromEntity(todo);
  }

  public void delete(Integer id) {
    todoRepository.deleteById(id);
  }

  public TodoResponse complete(Integer id) {
    Todo todo = todoRepository
        .findById(id)
        .orElseThrow(() -> TodoNotFoundException.withId(id));
    todo.setCompleted(true);
    return TodoResponse.fromEntity(todo);
  }

  public List<TodoResponse> clearCompleted() {
    todoRepository.clearCompletedTodos();
    return todoRepository.findAllUnclearedTodos();
  }

  public List<TodoResponse> toggleComplete() {
    todoRepository.toggleComplete();
    return todoRepository.findAllUnclearedTodos();
  }

}
