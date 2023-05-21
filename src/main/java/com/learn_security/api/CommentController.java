package com.learn_security.api;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.CommentRequest;
import com.learn_security.dto.GetCommentRequest;
import com.learn_security.dto.CommentResponse;
import com.learn_security.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentResponse>> getCommentByBook(@RequestParam Long id){
        return new ResponseEntity<>(commentService.getCommentByBookId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCommentToBook(@RequestBody CommentRequest commentRequest){
        return new ResponseEntity<>(commentService.addCommentToBook(commentRequest), HttpStatus.CREATED);
    }
}
