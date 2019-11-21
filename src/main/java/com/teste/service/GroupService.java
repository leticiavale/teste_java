package com.teste.service;

import org.springframework.data.domain.Page;

import com.teste.bean.GroupBean;

public interface GroupService {
	
	public Page<GroupBean> getAll(String keyword, Boolean showInactiveRegisters, Integer page, Integer pageSize);
	
	public GroupBean get(Long id);
	
	public GroupBean create(GroupBean groupBean);
	
	public GroupBean update(Long id, GroupBean groupBean);
	
	public void delete(Long id);
	
}
