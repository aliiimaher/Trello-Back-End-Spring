package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Comment;
import project.trello.service.CommentService;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/delete-comment/{comment_id}")
    public void editComment(@PathVariable("comment_id") Long comment_id) {
        commentService.deleteComment(comment_id);
    }

    @PutMapping("/edit-comment/{comment_id}")
    public Comment editComment(@PathVariable Long comment_id, @RequestBody Comment comment){
        return commentService.editComment(comment_id, comment);
    }

}
