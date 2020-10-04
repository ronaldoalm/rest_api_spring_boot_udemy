package br.com.erudio.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {

	
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setGender(person.getGender());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setBirthdate(person.getBirthDate());
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 personVOV2) {
		Person person = new Person();
		person.setId(personVOV2.getId());
		person.setBirthDate(new Date());
		person.setFirstName(personVOV2.getFirstName());
		person.setLastName(personVOV2.getLastName());
		person.setGender(personVOV2.getGender());
		person.setAddress(personVOV2.getAddress());
		return person;
	}
	
}
