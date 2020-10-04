package br.com.erudio.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonConverter personConverter;

	public PersonVOV1 findById(Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(person, PersonVOV1.class);
	}

	public PersonVOV1 create(PersonVOV1 personVO) {
		Person person = DozerConverter.parseObject(personVO, Person.class);
		return DozerConverter.parseObject(personRepository.save(person), PersonVOV1.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVOV2) {
		Person person = personConverter.convertVoToEntity(personVOV2);
		return personConverter.convertEntityToVO(personRepository.save(person));
	}
	
	public List<PersonVOV1> findAll() {
		return DozerConverter.parseListObjects(personRepository.findAll(), PersonVOV1.class) ;
	}

	public PersonVOV1 update(PersonVOV1 p) {
		Person person = personRepository.findById(p.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		person.setAddress(p.getAddress());
		person.setFirstName(p.getFirstName());
		person.setGender(p.getGender());
		person.setLastName(p.getLastName());
		
		return DozerConverter.parseObject(personRepository.save(person), PersonVOV1.class);
	}
	
	@Transactional
	public PersonVOV1 disabledPerson(Long id) {
		personRepository.disabledPersons(id);
		
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(person, PersonVOV1.class);
	}
	
	public void delete(Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		personRepository.delete(person);
	}


}
