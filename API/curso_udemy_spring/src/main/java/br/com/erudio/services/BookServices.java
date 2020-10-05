package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVOV1;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository bookRepository;

	public BookVOV1 findById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(book, BookVOV1.class);
	}

	public BookVOV1 create(BookVOV1 bookVO) {
		Book book = DozerConverter.parseObject(bookVO, Book.class);
		return DozerConverter.parseObject(bookRepository.save(book), BookVOV1.class);
	}

	public List<BookVOV1> findAll() {
		return DozerConverter.parseListObjects(bookRepository.findAll(), BookVOV1.class);
	}
	
	public Page<BookVOV1> findAllByPage(Pageable pageable) {
		var pages = bookRepository.findAll(pageable);
		return pages.map(this::convertToBookVOV1);
	}
	
	private BookVOV1 convertToBookVOV1(Book book) {
		return DozerConverter.parseObject(book, BookVOV1.class);
	}

	public BookVOV1 update(BookVOV1 p) {
		Book book = bookRepository.findById(p.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		book.setAuthor(p.getAuthor());
		book.setLaunchDate(p.getLaunchDate());
		book.setPrice(p.getPrice());
		book.setTitle(p.getTitle());

		return DozerConverter.parseObject(bookRepository.save(book), BookVOV1.class);
	}

	public void delete(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		bookRepository.delete(book);
	}

}
