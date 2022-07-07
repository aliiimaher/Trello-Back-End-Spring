package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Comment;
import project.trello.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(Long comment_id) {
        boolean exists = commentRepository.existsById(comment_id);
        if (!exists) {
            throw new IllegalStateException("comment with id " +
                    comment_id + " does not exist!");
        }
    }

    public Comment editComment(Long comment_id, Comment comment) {
        Comment foundedComment = commentRepository.findById(comment_id).get();
        foundedComment.setText(comment.getText());

        return commentRepository.save(foundedComment);
    }
}
