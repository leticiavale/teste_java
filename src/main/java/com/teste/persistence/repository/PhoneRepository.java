package com.teste.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.teste.persistence.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone,Long>, 
											QuerydslPredicateExecutor<Phone>  {
}
