package com.enablehr.challenge.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enablehr.challenge.java.entity.TodoGroup;

public interface TodoGroupRepository extends JpaRepository<TodoGroup, Integer>{
 
  Optional<TodoGroup> findByGroupName(String groupName);
}
