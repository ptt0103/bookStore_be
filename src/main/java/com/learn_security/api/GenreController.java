package com.learn_security.api;

import com.learn_security.models.Genre;
import com.learn_security.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres(){
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }
}
