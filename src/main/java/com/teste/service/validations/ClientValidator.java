package com.teste.service.validations;

import com.teste.bean.ClientBean;
import com.teste.enums.ClientType;
import com.teste.rest.advice.exceptions.InvalidFieldException;

public class ClientValidator {
	
	public static void validateCreation(ClientBean clientBean) {		
		if (clientBean.getType() == null) {
			throw new InvalidFieldException("Informe o tipo do cliente");
		}
		
		if (clientBean.getCpg() == null || clientBean.getCpg().isEmpty() || clientBean.getCpg().length() > 18) {
			if (clientBean.getType() == ClientType.FISICAL) {
				throw new InvalidFieldException("O CPF do cliente é obrigatório e deve possuir até 14 caracteres");
			} else {
				throw new InvalidFieldException("O CNPJ do cliente é obrigatório e deve possuir até 18 caracteres");
			}
		}

		generalValidation(clientBean);
	}
	
	public static void validateUpdate(ClientBean clientBean) {
		generalValidation(clientBean);
	}
	
	private static void generalValidation(ClientBean clientBean) {
		if (clientBean.getName() == null || clientBean.getName().isEmpty() || clientBean.getName().length() > 30) {
			throw new InvalidFieldException("O nome do cliente é obrigatório e deve possuir até 80 caracteres");
		}
		
		if (clientBean.getGroup() == null || clientBean.getGroup().getId() == null || clientBean.getGroup().getId() == 0) {
			throw new InvalidFieldException("O cliente deve possuir um grupo");
		}
	}
	
}
