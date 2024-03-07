package ru.smpas.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smpas.springcourse.models.Book;
import ru.smpas.springcourse.models.Person;
import ru.smpas.springcourse.repositories.BookRepository;
import ru.smpas.springcourse.repositories.PersonRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> index(Integer page, Integer booksPerPage, Boolean sortByYear) {
        if (page != null && booksPerPage != null) {
            if (sortByYear == null || !sortByYear) {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
            } else {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
            }
        }

        if (sortByYear == null || !sortByYear) {
            return bookRepository.findAll();
        } else {
            return bookRepository.findAll(Sort.by("year"));
        }
    }

    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public void releaseBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return;
        }

        book.setOwner(null);
    }

    @Transactional(readOnly = false)
    public void setBookOwner(int bookId, int ownerId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Person owner = personRepository.findById(ownerId).orElse(null);

        if (book == null || owner == null) {
            return;
        }

        book.setOwner(owner);
    }

    public Book searchBook(String query) {
        return bookRepository.findFirstByNameStartsWith(query);
    }
}