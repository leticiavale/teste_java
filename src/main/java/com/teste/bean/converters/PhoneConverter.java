package com.teste.bean.converters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.teste.bean.PhoneBean;
import com.teste.persistence.entity.Client;
import com.teste.persistence.entity.Phone;
import com.teste.rest.advice.exceptions.InvalidFieldException;

public class PhoneConverter {

	public static PhoneBean toBean(Phone phone) {
		
		if (phone == null) {
			return null;
		}
		
		PhoneBean phoneBean = new PhoneBean();

		phoneBean.setPhoneNumber(phone.getPhoneNumber());
		
		return phoneBean;
		
	}

	public static List<PhoneBean> toBean(Set<Phone> phones) {	
		
		if (phones == null || phones.size() == 0) {
			return null;
		}
		
		return phones.stream().map(phone->toBean(phone)).collect(Collectors.toList());		
	}

	public static Phone toEntity(PhoneBean phoneBean, Client client) {
		
		if (phoneBean == null) {
			return null;
		}
		
		if (phoneBean.getPhoneNumber() == null || phoneBean.getPhoneNumber().isEmpty() || phoneBean.getPhoneNumber().length() > 13) {
			throw new InvalidFieldException("O número de telefone é obrigatório e deve possuir até 13 caracteres");
		}
		
		Phone phone = new Phone();

		phone.setClient(client);
		phone.setPhoneNumber(phoneBean.getPhoneNumber());
		
		return phone;
		
	}

	public static void toEntity(List<PhoneBean> phones, Client client) {	
		
		if (phones == null || phones.size() == 0) {
			return;
		}
		
		client.getPhones().addAll(
			phones.stream().map(phoneBean->toEntity(phoneBean, client)).collect(Collectors.toSet())
		);
	}
	
}
