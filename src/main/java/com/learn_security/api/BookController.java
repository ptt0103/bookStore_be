package com.learn_security.api;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.BookDto;
import com.learn_security.dto.FilterDto;
import com.learn_security.dto.SearchDto;
import com.learn_security.models.Book;
import com.learn_security.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.ok().body(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<Book> getByName(@RequestBody String name){
        return ResponseEntity.ok().body(bookService.getByName(name));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Book>> getSearch(@RequestBody SearchDto searchDto){
        return new ResponseEntity<>(bookService.search(searchDto), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> getByGenres(@RequestBody FilterDto filterDto){
        return ResponseEntity.ok().body(bookService.getByGenres(filterDto));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok().body(bookService.addNewBook(bookDto));
    }
}
