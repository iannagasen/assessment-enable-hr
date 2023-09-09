package com.enablehr.challenge.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.enablehr.challenge.java.entity.Todo;
import com.enablehr.challenge.java.entity.TodoGroup;
import com.enablehr.challenge.java.repository.TodoGroupRepository;
import com.enablehr.challenge.java.repository.TodoRepository;

@Component
public class DataInitializer implements CommandLineRunner {

  private final TodoRepository todoRepository;
  private final TodoGroupRepository todoGroupRepository;

  public DataInitializer(TodoRepository todoRepository, TodoGroupRepository todoGroupRepository) {
    this.todoRepository = todoRepository;
    this.todoGroupRepository = todoGroupRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Todo t1 = new Todo();
    t1.setCompleted(false);
    t1.setText("Use Redux");
    todoRepository.save(t1);

    Todo t2 = new Todo();
    t2.setCompleted(false);
    t2.setText("Use React");
    todoRepository.save(t2);

    Todo t3 = new Todo();
    t3.setCompleted(false);
    t3.setText("Pass the test");
    todoRepository.save(t3);

    // Create group
    TodoGroup g1 = new TodoGroup();
    g1.setGroupName("DEFAULT");
    todoGroupRepository.save(g1);
  }
  
}
