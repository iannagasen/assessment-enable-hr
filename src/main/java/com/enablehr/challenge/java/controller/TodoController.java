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

import com.enablehr.challenge.java.dto.TodoBatchUpdateRequest;
import com.enablehr.challenge.java.dto.TodoCreateRequest;
import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.dto.TodoUpdateRequest;
import com.enablehr.challenge.java.exceptions.UnknownTodoActionException;
import com.enablehr.challenge.java.service.TodoService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TodoController {
  
 private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/todos")
  public List<TodoResponse> getAllTodos() {
    return todoService.getAllTodos();
  }

  @PostMapping("/todos")
  public TodoResponse createTodo(@RequestBody TodoCreateRequest req) {
    return todoService.create(req);
  }

  @PutMapping("/todos/{id}")
  public TodoResponse updateTodo(@PathVariable Integer id, @RequestBody TodoUpdateRequest req) {
    return todoService.update(id, req);
  }

  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable Integer id) {
    todoService.delete(id);
  }

  @PatchMapping("/todos/{id}")
  public TodoResponse updateCompleteTag(@PathVariable Integer id, @RequestParam boolean completed) {
    return todoService.updateCompleteTag(id, completed);
  }

  @PutMapping("/todos")
  public List<TodoResponse> executeTodoAction(@RequestBody TodoBatchUpdateRequest req) {
    switch (req.getAction()) {
      case "clear": return todoService.clearCompleted();
      case "complete": return todoService.completeAll();
      case "uncomplete": return todoService.uncompleteAll();
      default: throw UnknownTodoActionException.withAction(req.getAction());
    }
  }

}
