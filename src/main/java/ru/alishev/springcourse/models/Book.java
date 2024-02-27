package ru.alishev.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 40, message = "Book name should be between 2 and 40 characters")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, max = 40, message = "Author name should be between 2 and 40 characters")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    @Max(value = 2024, message = "Year shouldn't be greater than 2024")
    private int year;

    private Person owner;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
