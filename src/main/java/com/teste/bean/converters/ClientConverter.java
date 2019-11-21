package com.teste.bean.converters;

import com.teste.bean.ClientBean;
import com.teste.enums.ClientType;
import com.teste.persistence.entity.Client;
import com.teste.rest.advice.exceptions.InvalidFieldException;

public class ClientConverter {

	public static ClientBean toBean(Client client) {
		
		if (client == null) {
			return null;
		}
		
		ClientBean clientBean = new ClientBean();
		
		clientBean.setId(client.getId());
		clientBean.setCpg(client.getCpg());
		clientBean.setGroup(GroupConverter.toBean(client.getGroup()));
		clientBean.setIdentity(client.getIdentity());
		clientBean.setName(client.getName());
		clientBean.setPhones(PhoneConverter.toBean(client.getPhones()));
		clientBean.setStatus(client.getStatus());
		clientBean.setType(client.getType());
		
		return clientBean;
		
	}

	public static Client toEntity(ClientBean clientBean) {
		
		if (clientBean == null) {
			return null;
		}
				
		Client client = new Client();

		client.setId(clientBean.getId());
		client.setCpg(clientBean.getCpg());
		client.setGroup(GroupConverter.toEntity(clientBean.getGroup()));
		client.setIdentity(clientBean.getIdentity());
		client.setName(clientBean.getName());
		client.setStatus(clientBean.getStatus());
		client.setType(clientBean.getType());

		PhoneConverter.toEntity(clientBean.getPhones(),client);
		
		return client;
		
	}
	
}
