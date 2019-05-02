package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.CommentRepository;
import com.khoubyari.example.domain.Comment;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController extends AbstractRestHandler{
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    Comment createComment(@RequestBody Comment comment){
        comment.setCreatedDatetime(LocalDateTime.now());
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
        return commentRepository.save(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComments(@PathVariable("id") Long id){
        checkResourceFound(commentRepository.findOne(id));
        commentRepository.delete(id);
    }
}
