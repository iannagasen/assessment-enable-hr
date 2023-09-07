package com.enablehr.challenge.java.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enablehr.challenge.java.dto.TodoCreateRequest;
import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.dto.TodoUpdateRequest;
import com.enablehr.challenge.java.exceptions.UnknownTodoActionException;
import com.enablehr.challenge.java.service.TodoService;

@RestController
@CrossOrigin
@RequestMapping("/api/todos")
public class TodoTestController {
  
  private final TodoService todoService;

  public TodoTestController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public List<TodoResponse> getAllTodos() {
    return todoService.getAllTodos();
  }

  @PostMapping
  public TodoResponse createTodo(@RequestBody TodoCreateRequest req) {
    return todoService.create(req);
  }

  @PutMapping("/{id}")
  public TodoResponse updateTodo(@PathVariable Integer id, @RequestBody TodoUpdateRequest req) {
    return todoService.update(id, req);
  }

  @DeleteMapping("/{id}")
  public void deleteTodo(@PathVariable Integer id) {
    todoService.delete(id);
  }

  @PatchMapping("/{id}")
  public TodoResponse updateCompleteTag(@PathVariable Integer id, @RequestParam boolean completed) {

    return todoService.updateCompleteTag(id, completed);
  }

  @PutMapping("/action/{action}")
  public List<TodoResponse> executeTodoAction(@PathVariable String action) {
    switch (action) {
      case "clear-completed": return todoService.clearCompleted();
      case "toggle-complete": return todoService.toggleComplete();
      default: throw UnknownTodoActionException.withAction(action);
    }
  }

}
