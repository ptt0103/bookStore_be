package com.learn_security.service;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.BookDto;
import com.learn_security.dto.FilterDto;
import com.learn_security.dto.SearchDto;
import com.learn_security.models.Book;
import com.learn_security.models.Genre;
import com.learn_security.repository.BookRepository;
import com.learn_security.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).get();
    }


    public Book getByName(String name) {
        return bookRepository.findByName(name).get();
    }

    public List<Book> getByGenres(FilterDto filterDto) {
        List<Book> filter = new ArrayList<>();
        for (Long i : filterDto.getGenreIds()
             ) {
            List<Book> books = bookRepository.findByGenre(i);
            filter.addAll(books);
        }

        return filter;
    }

    public ApiResponse addNewBook(BookDto bookDto) {
        System.out.println(bookDto);
        Genre genre = genreRepository.findById(bookDto.getGenreId()).get();
        Book book = Book.builder()
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .imageUrl(bookDto.getImageUrl())
                .publisher(bookDto.getPublisher())
                .name(bookDto.getName())
                .genre(genre)
                .build();
        bookRepository.save(book);
        return ApiResponse.builder()
                .message("add success")
                .build();
    }

    public List<Book> search(SearchDto searchDto) {
        return bookRepository.findByNameContainingIgnoreCase(searchDto.getName());
    }
}
