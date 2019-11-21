package com.teste.bean;

import java.sql.Timestamp;
import java.util.List;

import com.teste.enums.ClientType;
import com.teste.enums.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientBean {

	private Long id;

	private String name;

	private ClientType type;

	private String cpg;

	private String identity;
	
	private Timestamp createdDate;
	
	private GroupBean group;

	private Status status;
	
	private List<PhoneBean> phones;
	
}
