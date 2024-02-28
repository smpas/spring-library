package ru.smpas.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.smpas.springcourse.dao.BookDao;
import ru.smpas.springcourse.dao.PersonDAO;
import ru.smpas.springcourse.models.Book;
import ru.smpas.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDao bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        System.out.println(model);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        Book book = bookDao.show(id);
        Person owner = bookDao.showBookOwner(id);
        if (owner != null) {
            book.setOwner(owner);
        }
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable int id) {
        bookDao.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{bookId}/new-owner")
    public String setOwner(@PathVariable("bookId") int bookId,
                           @ModelAttribute("person") Person person) {
        bookDao.setOwner(bookId, person.getId());
        return "redirect:/books/{bookId}";
    }
}
