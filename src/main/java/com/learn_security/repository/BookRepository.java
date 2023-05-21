package com.learn_security.repository;

import com.learn_security.models.Book;
import com.learn_security.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    List<Book> findByNameContainingIgnoreCase(String name);

    @Query("select b from Book b where b.genre.id =:id")
    List<Book> findByGenre(@Param("id") Long id);
}
