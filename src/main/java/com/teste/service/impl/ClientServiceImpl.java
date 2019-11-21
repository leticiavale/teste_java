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
import com.teste.bean.ClientBean;
import com.teste.bean.GroupBean;
import com.teste.bean.converters.ClientConverter;
import com.teste.enums.ClientType;
import com.teste.enums.Status;
import com.teste.persistence.entity.Client;
import com.teste.persistence.entity.QClient;
import com.teste.persistence.repository.ClientRepository;
import com.teste.rest.advice.exceptions.AlreadyExistsException;
import com.teste.rest.advice.exceptions.NotFoundException;
import com.teste.service.ClientService;
import com.teste.service.GroupService;
import com.teste.service.validations.ClientValidator;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	GroupService groupService;

	@Override
	public Page<ClientBean> getAll(String keyword, Boolean showInactiveRegisters, Integer page, Integer pageSize) {
		keyword = keyword == null ? "" : keyword;
		
		BooleanExpression expression = QClient.client.name.containsIgnoreCase(keyword)
										.or(QClient.client.cpg.containsIgnoreCase(keyword));	
		
		if (!showInactiveRegisters) {
			expression = expression.and(QClient.client.status.eq(Status.ACTIVE));
		}
		
		Page<Client> pageClients = clientRepository.findAll(expression,PageRequest.of(page, pageSize));
		
		List<ClientBean> clientsBean = pageClients.getContent().stream().map(client->ClientConverter.toBean(client)).collect(Collectors.toList());
		
		return new PageImpl<ClientBean>(clientsBean, pageClients.getPageable(),pageClients.getTotalElements());
	}

	@Override
	public ClientBean get(Long id) {
		if(id == null) {
			return null;
		}
		
		Optional<Client> optionalClient = clientRepository.findById(id);
		
		if (optionalClient.isPresent()) {
			return ClientConverter.toBean(optionalClient.get());
		}
		
		return null;
	}

	private ClientBean get(String cpg) {
		if(cpg == null) {
			return null;
		}
		
		BooleanExpression expression = QClient.client.cpg.eq(cpg);
		
		Optional<Client> optionalClient = clientRepository.findOne(expression);
		
		if (optionalClient.isPresent()) {
			return ClientConverter.toBean(optionalClient.get());
		}
		
		return null;
	}

	@Override
	public ClientBean create(ClientBean clientBean) {
		
		ClientValidator.validateCreation(clientBean);
		
		if (get(clientBean.getCpg()) != null) {
			if (clientBean.getType() == ClientType.FISICAL) {
				throw new AlreadyExistsException("Esse CPF já está cadastrado");
			} else {
				throw new AlreadyExistsException("Esse CNPJ já está cadastrado");
			}
		}
		
		if (groupService.get(clientBean.getGroup().getId()) == null) {
			throw new NotFoundException("Informe um grupo válido");
		}

		Client client = ClientConverter.toEntity(clientBean);
		
		client.setId(null);
		client.setStatus(Status.ACTIVE);
		
		return ClientConverter.toBean(clientRepository.save(client));
		
	}

	@Override
	public ClientBean update(Long id, ClientBean clientBean) {
		
		ClientValidator.validateUpdate(clientBean);
		
		if (groupService.get(clientBean.getGroup().getId()) == null) {
			throw new NotFoundException("Informe um grupo válido");
		}
		
		ClientBean clientToUpdate = get(id);	
		
		if (clientToUpdate == null) {
			throw new NotFoundException("Cliente não encontrado");
		}
		
		clientToUpdate.setName(clientBean.getName());
		clientToUpdate.setIdentity(clientBean.getIdentity());
		clientToUpdate.setGroup(clientBean.getGroup());
		clientToUpdate.setPhones(clientBean.getPhones());

		Client client = ClientConverter.toEntity(clientToUpdate);
		
		return ClientConverter.toBean(clientRepository.save(client));
	}

	@Override
	public void delete(Long id) {
		ClientBean clientToDelete = get(id);
		clientToDelete.setStatus(Status.INACTIVE);

		Client client = ClientConverter.toEntity(clientToDelete);
		
		clientRepository.save(client);		
	}
	
	
	
}
