package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.RequestRepository;
import com.khoubyari.example.domain.Request;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/requests")
public class RequestController extends AbstractRestHandler {
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    Request createRequest(@RequestBody Request request){
        return requestRepository.save(request);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Request> getRequests(){
        return (ArrayList<Request>) requestRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Request retrieveRequest(@PathVariable("id") Long id) {
        Request Request = requestRepository.findOne(id);
        checkResourceFound(Request);
        return Request;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Request updateRequests(@PathVariable("id") Long id, @RequestBody Request request){
        checkResourceFound(requestRepository.findOne(id));
        if (id != request.getId()) throw new DataFormatException("ID doesn't match!");
        return requestRepository.save(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequests(@PathVariable("id") Long id){
        checkResourceFound(requestRepository.findOne(id));
        requestRepository.delete(id);
    }
}
