package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.CommentRepository;
import com.khoubyari.example.domain.Comment;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController extends AbstractRestHandler{
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    Comment createComment(@RequestBody Comment comment, @AuthenticationPrincipal UserDetails userDetails){
        comment.setCreatedDatetime(LocalDateTime.now());
        comment.setAuthorId(((User)userDetails).getId());
        return commentRepository.save(comment);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Comment> getComments(){
        return (ArrayList<Comment>) commentRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Comment retrieveComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.findOne(id);
        checkResourceFound(comment);
        return comment;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Comment updateComments(@PathVariable("id") Long id, @RequestBody Comment comment){
        checkResourceFound(commentRepository.findOne(id));
        if (id != comment.getId()) throw new DataFormatException("ID doesn't match!");
        Comment oldComment = commentRepository.findOne(id);
        LocalDateTime oldValue = LocalDateTime.parse(oldComment.getCreatedDatetime());
        comment.setCreatedDatetime(oldValue);
        comment.setAuthorId(oldComment.getAuthorId());
        return commentRepository.save(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComments(@PathVariable("id") Long id){
        checkResourceFound(commentRepository.findOne(id));
        commentRepository.delete(id);
    }
}
