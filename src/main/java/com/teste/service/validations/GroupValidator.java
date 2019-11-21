package com.teste.service.validations;

import com.teste.bean.GroupBean;
import com.teste.rest.advice.exceptions.InvalidFieldException;

public class GroupValidator {
	
	public static void validate(GroupBean groupBean) {
		if (groupBean.getName() == null || groupBean.getName().isEmpty() || groupBean.getName().length() > 30) {
			throw new InvalidFieldException("O nome do grupo é obrigatório e deve possuir até 30 caracteres");
		}
	}
	
}
