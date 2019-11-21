package com.teste.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.teste.persistence.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Long>, 
											QuerydslPredicateExecutor<Group>  {
}
