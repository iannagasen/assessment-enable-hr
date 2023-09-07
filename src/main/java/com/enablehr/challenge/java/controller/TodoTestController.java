package com.enablehr.challenge.java.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enablehr.challenge.java.dto.TodoCreateRequest;
import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.dto.TodoUpdateRequest;
import com.enablehr.challenge.java.service.TodoService;

@RestController
@CrossOrigin
@RequestMapping("/test") // temporary
public class TodoTestController {
  
  private final TodoService todoService;

  public TodoTestController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/todos")
  public List<TodoResponse> getAllTodos() {
    return todoService.getAllTodos();
  }

  @PostMapping("/todo")
  public TodoResponse createTodo(@RequestBody TodoCreateRequest req) {
    return todoService.create(req);
  }

  @PutMapping("/todo")
  public TodoResponse updateTodo(@RequestBody TodoUpdateRequest req) {
    return todoService.update(req);
  }

  @DeleteMapping("/todo/{id}")
  public void deleteTodo(@PathVariable Integer id) {
    todoService.delete(id);
  }

  @PostMapping("/todo/complete/{id}")
  public TodoResponse completeTodo(@PathVariable Integer id) {
    return todoService.complete(id);
  }

  @PostMapping("/todos/clear")
  public List<TodoResponse> clearCompletedTodos() {
    return todoService.clearCompleted();
  }

  @PostMapping("/todos/complete")
  public List<TodoResponse> toggleCompleteTodos() {
    return todoService.toggleComplete();
  }
  
}
