package com.enablehr.challenge.java.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;


@RestController
@CrossOrigin
public class TodoInMemoryController {

  static final String COMPLETED = "completed";

  static final String ID = "id";

  static final String TEXT = "text";

  private final List<Map<String, Object>> todos = new LinkedList<>();

  private int counter = 0;

  @RequestMapping(path = "/todos/clear", method = RequestMethod.POST)
  public List<Map<String, Object>> clearCompleted() {

    todos.removeIf(todo -> todo.get(COMPLETED) == Boolean.TRUE);

    return todos;
  }

  @RequestMapping(path = "/todos/complete", method = RequestMethod.POST)
  public List<Map<String, Object>> completeAll() {

    boolean complete = todos.stream()
                            .map(todo -> todo.get(COMPLETED))
                            .anyMatch(Boolean.FALSE::equals);

    todos.forEach(todo -> todo.put(COMPLETED, complete));

    return todos;
  }

  @RequestMapping(path = "/todo", method = RequestMethod.POST)
  public Map<String, Object> create(@RequestBody Map<String, Object> body) {

    Map<String, Object> todo = new HashMap<>();

    todo.put(ID, counter++);
    todo.put(COMPLETED, Boolean.FALSE);
    todo.put(TEXT, body.get(TEXT));

    todos.add(todo);

    return todo;
  }

  @RequestMapping(path = "/todo", method = RequestMethod.DELETE)
  public void delete(@RequestBody Map<String, Object> body) {

    Integer id = (Integer) body.get(ID);

    if (id != null) {
      todos.removeIf(todo -> id.equals(todo.get(ID)));
    }
  }

  @PostConstruct
  public void init() {

    todos.clear();
    counter = 0;

    Stream.of("Use Redux", "Use React", "Pass the Test")
          .map(text -> {
            final HashMap<String, Object> todo = new HashMap<>();

            todo.put(ID, counter++);
            todo.put(COMPLETED, Boolean.FALSE);
            todo.put(TEXT, text);

            return todo;
          })
          .collect(toCollection(() -> todos));
  }

  @RequestMapping(path = "/todos", method = RequestMethod.GET)
  public List<Map<String, Object>> list() {

    return todos;
  }

  @RequestMapping(path = "/todo/complete", method = RequestMethod.POST)
  public Map<String, Object> markAsComplete(@RequestBody Map<String, Object> body) {

    Integer id = (Integer) body.get(ID);

    Map<String, Object> changed = findById(id);

    if (changed != null) {
      final Boolean completed = (Boolean) changed.get(COMPLETED);
      changed.put(COMPLETED, !completed);
    }

    return changed;
  }

  @RequestMapping(path = "/todo", method = RequestMethod.PUT)
  public Map<String, Object> update(@RequestBody Map<String, Object> body) {

    Integer id = (Integer) body.get(ID);

    String text = (String) body.get(TEXT);

    Map<String, Object> changed = findById(id);

    if (changed != null) {
      changed.put(TEXT, text);
    }

    return changed;
  }

  List<Map<String, Object>> getTodos() {

    return todos;
  }

  private Map<String, Object> findById(Integer id) {

    if (id == null) {
      return null;
    }

    return todos.stream()
                .filter(todo -> id.equals(todo.get(ID)))
                .findFirst()
                .orElse(null);
  }
}
