package ru.smpas.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.smpas.springcourse.models.Book;
import ru.smpas.springcourse.models.Person;
import ru.smpas.springcourse.services.BookService;
import ru.smpas.springcourse.services.PersonService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(name = "sort_by_year", required = false) Boolean sortByYear) {
        model.addAttribute("books", bookService.index(page, booksPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        Book book = bookService.show(id);
        List<Person> people = personService.index();

        model.addAttribute("book", book);
        model.addAttribute("people", people);
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

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable int id) {
        bookService.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{bookId}/new-owner")
    public String setOwner(@PathVariable("bookId") int bookId,
                           @ModelAttribute("person") Person person) {
        bookService.setBookOwner(bookId, person.getId());
        return "redirect:/books/{bookId}";
    }

    @GetMapping("/search")
    public String searchPage(Model model, @RequestParam(name = "query", required = false) String query) {
        if (query != null) {
            model.addAttribute("book", bookService.searchBook(query));
        }
        return "/books/search";
    }
}
