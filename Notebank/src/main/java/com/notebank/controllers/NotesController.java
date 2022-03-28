package com.notebank.controllers;

import com.notebank.models.dto.LoginDto;
import com.notebank.models.dto.NotesDto;
import com.notebank.models.dto.UserDto;
import com.notebank.services.NotesService;
import com.notebank.services.UsersService;
import com.notebank.utils.ResponseObject;
import com.notebank.utils.ValidatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Handle all request for note management
@RestController
@RequestMapping(value = "notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    //The user service is used within the code to authenticate a user's accesstoken
    @Autowired
    private UsersService usersService;

    //POST http request are used for create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createNotes(@RequestBody NotesDto notesDto,
            @RequestHeader("accessToken") String accessToken) {
        Integer validateUser = usersService.validateUser(accessToken);
        if (validateUser != null) {
            return notesService.createNotes(notesDto, validateUser);
        } else {
            return new ResponseObject(HttpStatus.UNAUTHORIZED)
                    .HttpResponse();
        }
    }

    //PUT method is used to update notes
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateNotes(@RequestBody NotesDto notesDto,
            @RequestHeader("accessToken") String accessToken) {

        Integer validateUser = usersService.validateUser(accessToken);
        if (validateUser != null) {
            return notesService.updateNotes(notesDto, validateUser);
        } else {
            return new ResponseObject(HttpStatus.UNAUTHORIZED)
                    .HttpResponse();
        }
    }

    //GET method is used to get notes
    //passing the id of the note to be viewed
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> viewNote(
            @RequestHeader(value = "accessToken", required = false) String accessToken,
            @PathVariable(required = true) int id) {

        Integer validateUser = usersService.validateUser(accessToken);
        return notesService.viewNote(id, validateUser);

    }

    //DELETE method is used to delete notes
    //passing the id of the note to be deleted as a path parameter
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<?> deleteNote(
            @RequestHeader(value = "accessToken", required = true) String accessToken,
            @PathVariable(required = true) int id) {

        Integer userId = usersService.validateUser(accessToken);
        if (userId != null) {
            return notesService.deleteNotes(id, userId);
        } else {
            return new ResponseObject(HttpStatus.UNAUTHORIZED)
                    .HttpResponse();
        }

    }

    //GET method to filter notes
    //this method contains query parameters to optionally filter notes
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> fetchNotes(
            @RequestHeader(value = "accessToken", required = false) String accessToken,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String visibility,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String orderColumn,
            @RequestParam(defaultValue = "") String orderDirection) {

        Integer userId = usersService.validateUser(accessToken);

        return notesService.fetchNotes(id, userId, title, body, tags, visibility, page, size, orderColumn, orderDirection);

    }

}
