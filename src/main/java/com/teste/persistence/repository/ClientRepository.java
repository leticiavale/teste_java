package com.teste.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.teste.persistence.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long>, 
											QuerydslPredicateExecutor<Client>  {
}
