package br.com.erudio.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public List<PersonVOV1> findAll(Pageable pageable) {
		List<Person> persons = personRepository.findAll(pageable).getContent();
		return DozerConverter.parseListObjects(persons, PersonVOV1.class) ;
	}
	
	public Page<PersonVOV1> findAllBypage(Pageable pageable) {
		var page = personRepository.findAll(pageable);
		return page.map(this::convertToPersonVOV1) ;
	}
	
	public Page<PersonVOV1> findPersonByName(String firstName, Pageable pageable) {
		var page = personRepository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVOV1) ;
	}

	
	private PersonVOV1 convertToPersonVOV1(Person person) {
		return DozerConverter.parseObject(person, PersonVOV1.class);
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
