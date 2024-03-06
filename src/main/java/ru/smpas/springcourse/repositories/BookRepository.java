package ru.smpas.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smpas.springcourse.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
