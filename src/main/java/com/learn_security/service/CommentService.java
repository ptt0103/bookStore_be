package com.learn_security.service;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.CommentRequest;
import com.learn_security.dto.GetCommentRequest;
import com.learn_security.dto.CommentResponse;
import com.learn_security.models.Book;
import com.learn_security.models.Comment;
import com.learn_security.repository.BookRepository;
import com.learn_security.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    public List<CommentResponse> getCommentByBookId(Long id) {
        Book book = bookRepository.findById(id).get();
        List<CommentResponse> commentResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByBook(book);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Comment comment: comments
             ) {
            CommentResponse response = CommentResponse.builder()
                    .content(comment.getContent())
                    .email(comment.getEmail())
                    .creatAt(simpleDateFormat.format(comment.getCreateAt()))
                    .build();
            commentResponses.add(response);
        }

        return commentResponses;
    }

    public ApiResponse addCommentToBook(CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Book book = bookRepository.findById(commentRequest.getBookId()).get();
        Comment comment = Comment.builder()
                .book(book)
                .content(commentRequest.getContent())
                .createAt(new Date())
                .email(authentication.getName())
                .build();
        commentRepository.save(comment);

        return ApiResponse.builder()
                .message("add comment success")
                .build();
    }
}
