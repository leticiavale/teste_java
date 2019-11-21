package com.teste.bean.converters;

import com.teste.bean.GroupBean;
import com.teste.persistence.entity.Group;
import com.teste.rest.advice.exceptions.InvalidFieldException;

public class GroupConverter {

	public static GroupBean toBean(Group group) {
		
		if (group == null) {
			return null;
		}
		
		GroupBean groupBean = new GroupBean();
		
		groupBean.setId(group.getId());
		groupBean.setName(group.getName());
		groupBean.setStatus(group.getStatus());
		
		return groupBean;
		
	}

	public static Group toEntity(GroupBean groupBean) {
		
		if (groupBean == null) {
			return null;
		}
		
		Group group = new Group();

		group.setId(groupBean.getId());
		group.setName(groupBean.getName());
		group.setStatus(groupBean.getStatus());
		
		return group;
		
	}
	
}
