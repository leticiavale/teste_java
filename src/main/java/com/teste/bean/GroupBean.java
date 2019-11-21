package com.teste.bean;

import com.teste.enums.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupBean {

	private Long id;

	private String name;

	private Status status;

	
}
