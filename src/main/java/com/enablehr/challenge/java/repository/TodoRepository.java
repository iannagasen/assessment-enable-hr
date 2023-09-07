package com.enablehr.challenge.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.enablehr.challenge.java.dto.TodoResponse;
import com.enablehr.challenge.java.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

  @Query(
    "SELECT new com.enablehr.challenge.java.dto.TodoResponse(t.id as id, t.text as text, t.completed as completed)" + "\n" +
    "FROM Todo t" + "\n" +
    "WHERE t.cleared = false")
  List<TodoResponse> findAllUnclearedTodos();

  @Modifying
  @Query("UPDATE Todo t SET t.cleared = true WHERE t.completed = true")
  void clearCompletedTodos();

  @Modifying
  @Query(
    "Update Todo t SET t.completed = " + "\n" + 
    "CASE WHEN EXISTS(SELECT 1 FROM Todo t2 WHERE t2.completed = false) THEN true ELSE false END")
  void toggleComplete();
}
