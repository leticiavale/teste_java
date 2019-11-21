package com.teste.service;

import org.springframework.data.domain.Page;

import com.teste.bean.ClientBean;

public interface ClientService {
	
	public Page<ClientBean> getAll(String keyword, Boolean showInactiveRegisters, Integer page, Integer pageSize);
	
	public ClientBean get(Long id);
	
	public ClientBean create(ClientBean clientBean);
	
	public ClientBean update(Long id, ClientBean clientBean);
	
	public void delete(Long id);
	
}
