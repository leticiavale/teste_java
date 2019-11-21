package com.teste.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.bean.GroupBean;
import com.teste.service.GroupService;

@RestController
@RequestMapping(path = "group", produces = "application/json")
@Configuration
@CrossOrigin
public class GroupController {
	
	@Autowired
	private GroupService groupService;

	@GetMapping
	public Page<GroupBean> getAll(@RequestParam(required=false) String keyword,
								@RequestParam(defaultValue = "false",required=false) Boolean showInactiveRegisters,
								@RequestParam(defaultValue = "0", required=false) Integer page,
								@RequestParam(defaultValue = "10", required=false) Integer pageSize) {
		
		return groupService.getAll(keyword, showInactiveRegisters, page, pageSize);
	}

	@GetMapping("/{id}")
	public GroupBean get(@PathVariable Long id) {
		return groupService.get(id);
	}
	
	@PostMapping
	public GroupBean create(@RequestBody GroupBean groupBean) {
		return groupService.create(groupBean);
	}
	
	@PutMapping("/{id}")
	public GroupBean update(@PathVariable Long id, @RequestBody GroupBean groupBean) {
		return groupService.update(id, groupBean);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		groupService.delete(id);
	}

}
