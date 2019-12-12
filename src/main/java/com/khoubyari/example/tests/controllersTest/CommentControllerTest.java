package com.khoubyari.example.tests.controllersTest;

import com.khoubyari.example.api.rest.CommentController;
import com.khoubyari.example.dao.jpa.CommentRepository;
import com.khoubyari.example.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentController commentController;

    @Test
    public void testGetCommentsList(){
        Comment comment = new Comment();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments.add(comment);
        when(commentRepository.findAll()).thenReturn(comments);
        ArrayList<Comment> result = commentController.getComments();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testRetrieveComment(){
        long id = 10;
        Comment responseComment = new Comment();
        responseComment.setId(id);
        responseComment.setAuthorId((long)10);
        when(commentRepository.findOne(id)).thenReturn(responseComment);
        Comment result = commentController.retrieveComment(id);
        assertEquals(result.getAuthorId(), responseComment.getAuthorId());
    }

    @Test
    public void testUpdateComment(){
        long searchId = 10;
        Comment requestComment = new Comment();
        requestComment.setId(searchId);
        requestComment.setText("123");
        requestComment.setCreatedDatetime(LocalDateTime.now());
        Comment updatedComment = new Comment();
        updatedComment.setId(searchId);
        updatedComment.setText("456");
        when(commentRepository.findOne(searchId)).thenReturn(requestComment);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);
        Comment result = commentController.updateComments(searchId, requestComment);
        assertEquals(result.getText(), updatedComment.getText());
    }

    @Test
    public void testDeleteComment(){
        long id = 10;
        Comment comment = new Comment();
        comment.setId(id);
        when(commentRepository.findOne(id)).thenReturn(comment);
        commentController.deleteComments(id);
        verify(commentRepository).delete(id);
    }
}
