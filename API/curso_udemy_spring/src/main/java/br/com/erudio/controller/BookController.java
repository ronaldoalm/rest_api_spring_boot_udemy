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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVOV1;
import br.com.erudio.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book EndPoint", description = "Book Controller", tags = { "BookEndPoint" })
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookServices bookServices;

	@Autowired
	private PagedResourcesAssembler<BookVOV1> assembler;

	@ApiOperation(value = "Find all Books")
	@GetMapping(value = "/v1", produces = { "application/json", "application/xml" })
	public List<BookVOV1> findAll() {
		List<BookVOV1> bookVOV1 = bookServices.findAll();
		bookVOV1.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return bookVOV1;
	}

	@ApiOperation(value = "Find all people required using Pageable.")
	@GetMapping(value = "/v1", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> findAllByPage(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<BookVOV1> bookVOV1 = bookServices.findAllByPage(pageable);
		bookVOV1.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

		PagedResources<?> resources = assembler.toResource(bookVOV1);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Find one book by Id")
	@GetMapping(produces = { "application/json", "application/xml" }, value = "/v1/{id}")
	public BookVOV1 findById(@PathVariable("id") Long id) {
		BookVOV1 bookVOV1 = bookServices.findById(id);
		bookVOV1.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVOV1;
	}

	@ApiOperation(value = "Create Book")
	@PostMapping(value = "/v1", produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public BookVOV1 create(@RequestBody BookVOV1 book) {
		BookVOV1 bookVOV1 = bookServices.create(book);
		bookVOV1.add(linkTo(methodOn(BookController.class).findById(bookVOV1.getKey())).withSelfRel());
		return bookVOV1;
	}

	@ApiOperation(value = "Update Book")
	@PutMapping(value = "/v1", produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public BookVOV1 update(@RequestBody BookVOV1 book) {
		return bookServices.update(book);
	}

	@ApiOperation(value = "Delete Book")
	@DeleteMapping(value = "/v1/{id}")
	public void delete(@PathVariable("id") Long id) {
		bookServices.delete(id);
	}

}
