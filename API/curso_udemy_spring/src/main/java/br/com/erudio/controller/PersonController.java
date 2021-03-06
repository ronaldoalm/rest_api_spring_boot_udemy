package br.com.erudio.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/person")
@Api(value = "Person Endpoint", description = "Person Controller" , tags = {"PersonEndPoint"})
public class PersonController {

	@Autowired
	private PersonServices personServices;
	
	@Autowired
	private PagedResourcesAssembler<PersonVOV1> assembler;
	
	@ApiOperation(value = "Find all people required")
	@GetMapping(value= "/v1", produces = {"application/json" , "application/xml"})
	public List<PersonVOV1> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
									@RequestParam(value="limit", defaultValue = "12") int limit,
									@RequestParam(value="direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection,"id"));
		List<PersonVOV1> personVOV1 = personServices.findAll(pageable);
		personVOV1.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return personVOV1;
	}
	
	@ApiOperation(value = "Find all people required using Pageable.")
	@GetMapping(value= "/v2", produces = {"application/json" , "application/xml"})
	public ResponseEntity<?> findAllByPage(
									@RequestParam(value = "page", defaultValue = "0") int page,
									@RequestParam(value="limit", defaultValue = "12") int limit,
									@RequestParam(value="direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection,"id"));
		Page<PersonVOV1> personVOV1 = personServices.findAllBypage(pageable);
		personVOV1.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(personVOV1);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find all people required using Pageable.")
	@GetMapping(value= "/v2/findPersonByName/{firstName}", produces = {"application/json" , "application/xml"})
	public ResponseEntity<?> findPersonByName(
									@PathVariable("firstName") String firstName,
									@RequestParam(value = "page", defaultValue = "0") int page,
									@RequestParam(value="limit", defaultValue = "12") int limit,
									@RequestParam(value="direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection,"id"));
		Page<PersonVOV1> personVOV1 = personServices.findPersonByName(firstName,pageable);
		personVOV1.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(personVOV1);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value = "Find one person by Id")
	@GetMapping(produces = {"application/json" , "application/xml"} ,value = "/v1/{id}")
	public PersonVOV1 findById(@PathVariable("id") Long id) {
		PersonVOV1 personVOV1 = personServices.findById(id);
		personVOV1.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVOV1;
	}
	
	@ApiOperation(value = "Set Atribute enabled to FALSE by ID")
	@PatchMapping(produces = {"application/json" , "application/xml"} ,value = "/v1/{id}")
	public PersonVOV1 disabledPerson(@PathVariable("id") Long id) {
		PersonVOV1 personVOV1 = personServices.disabledPerson(id);
		personVOV1.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVOV1;
	}
	
	@ApiOperation(value = "Create person")
	@PostMapping(value= "/v1", produces = {"application/json" , "application/xml"}, consumes = {"application/json" , "application/xml"})
	public PersonVOV1 create(@RequestBody PersonVOV1 person) {
		PersonVOV1 personVOV1 =  personServices.create(person);
		personVOV1.add(linkTo(methodOn(PersonController.class).findById(personVOV1.getKey())).withSelfRel());
		return personVOV1;
	}
	
	@ApiOperation(value = "Create person using new route V2, this route has a new parameter birthdate")
	@PostMapping(produces = {"application/json" , "application/xml"}, consumes = {"application/json" , "application/xml"}, value = "/V2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		System.out.println(person.getFirstName());
		return personServices.createV2(person);
	}
	
	@ApiOperation(value = "Update person")
	@PutMapping(value= "/v1", produces = {"application/json" , "application/xml"}, consumes = {"application/json" , "application/xml"})
	public PersonVOV1 update(@RequestBody PersonVOV1 person) {
		return personServices.update(person);
	}
	
	@ApiOperation(value = "Delete person")
	@DeleteMapping(value = "/v1/{id}")
	public void delete(@PathVariable("id") Long id) {
		personServices.delete(id);
	}
	

}
