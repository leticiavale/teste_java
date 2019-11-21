package com.teste.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.teste.bean.GroupBean;
import com.teste.bean.converters.GroupConverter;
import com.teste.enums.Status;
import com.teste.persistence.entity.Group;
import com.teste.persistence.entity.QGroup;
import com.teste.persistence.repository.GroupRepository;
import com.teste.service.GroupService;
import com.teste.service.validations.GroupValidator;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupRepository groupRepository;

	@Override
	public Page<GroupBean> getAll(String keyword, Boolean showInactiveRegisters, Integer page, Integer pageSize) {
		keyword = keyword == null ? "" : keyword;
		
		BooleanExpression expression = QGroup.group.name.containsIgnoreCase(keyword);	
		
		if (!showInactiveRegisters) {
			expression = expression.and(QGroup.group.status.eq(Status.ACTIVE));
		}
		
		Page<Group> pageGroups = groupRepository.findAll(expression,PageRequest.of(page, pageSize));
		
		List<GroupBean> groupsBean = pageGroups.getContent().stream().map(group->GroupConverter.toBean(group)).collect(Collectors.toList());
		
		return new PageImpl<GroupBean>(groupsBean, pageGroups.getPageable(),pageGroups.getTotalElements());
	}

	@Override
	public GroupBean get(Long id) {
		
		Optional<Group> optionalGroup = groupRepository.findById(id);
		
		if (optionalGroup.isPresent()) {
			return GroupConverter.toBean(optionalGroup.get());
		}
		
		return null;
	}

	@Override
	public GroupBean create(GroupBean groupBean) {
		
		GroupValidator.validate(groupBean);

		Group group = GroupConverter.toEntity(groupBean);
		
		group.setStatus(Status.ACTIVE);
		
		return GroupConverter.toBean(groupRepository.save(group));
		
	}

	@Override
	public GroupBean update(Long id, GroupBean groupBean) {
		
		GroupValidator.validate(groupBean);
		
		GroupBean groupToUpdate = get(id);		
		groupToUpdate.setName(groupBean.getName());

		Group group = GroupConverter.toEntity(groupToUpdate);
		
		return GroupConverter.toBean(groupRepository.save(group));
	}

	@Override
	public void delete(Long id) {
		GroupBean groupToDelete = get(id);
		groupToDelete.setStatus(Status.INACTIVE);

		Group group = GroupConverter.toEntity(groupToDelete);
		
		groupRepository.save(group);		
	}
	
	
	
}
