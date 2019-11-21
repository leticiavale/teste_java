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

import com.teste.bean.ClientBean;
import com.teste.service.ClientService;

@RestController
@RequestMapping(path = "client", produces = "application/json")
@Configuration
@CrossOrigin
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping
	public Page<ClientBean> getAll(@RequestParam(required=false) String keyword,
								@RequestParam(defaultValue = "false",required=false) Boolean showInactiveRegisters,
								@RequestParam(defaultValue = "0", required=false) Integer page,
								@RequestParam(defaultValue = "10", required=false) Integer pageSize) {
		
		return clientService.getAll(keyword, showInactiveRegisters, page, pageSize);
	}

	@GetMapping("/{id}")
	public ClientBean get(@PathVariable Long id) {
		return clientService.get(id);
	}
	
	@PostMapping
	public ClientBean create(@RequestBody ClientBean clientBean) {
		return clientService.create(clientBean);
	}
	
	@PutMapping("/{id}")
	public ClientBean update(@PathVariable Long id, @RequestBody ClientBean clientBean) {
		return clientService.update(id, clientBean);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	}

}
