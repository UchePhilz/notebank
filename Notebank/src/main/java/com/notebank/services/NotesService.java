/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.services;

import com.google.gson.Gson;
import com.notebank.models.Notes;
import com.notebank.models.dto.NotesDto;
import com.notebank.models.enums.NoteVisibility;
import com.notebank.repositories.NotesRepository;
import com.notebank.utils.NoteUtils;
import com.notebank.utils.Pagination;
import com.notebank.utils.ResponseObject;
import com.notebank.utils.ValidatorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * The NotesService class will contain all business logic for the note module
 *
 * Methods that returns ResponseEntity<?> will be used to satisfy http requests
 *
 * @author Uche Powers
 */
@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public NotesService() {
    }

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    //TODO the createNote method returns ResponseEntity which will be used to satisfied https requests
    public ResponseEntity<?> createNotes(NotesDto notesDto, int userId) {

        ResponseEntity<?> httpResponse = null;

        //validator class, runs a check on the notedto
        List<ValidatorMessage> validate = ValidatorMessage.validate(notesDto);

        //if there is not failed validation
        if (validate.isEmpty()) {

            //TODO: the notedto returns returns the note entity object to be save
            Notes notes = notesDto.returnNotes();
            //TODO: add userid to the note
            notes.setUserId(userId);

            //TODO: save the entity
            notesRepository.save(notes);

            httpResponse = new ResponseObject(HttpStatus.OK, notes).HttpResponse();

        } else {
            httpResponse = new ResponseObject(HttpStatus.BAD_REQUEST, validate).HttpResponse();
        }

        return httpResponse;

    }

    //TODO the updateNotes method returns ResponseEntity which will be used to satisfied https requests
    public ResponseEntity<?> updateNotes(NotesDto notesDto, Integer userId) {

        ResponseEntity<?> httpResponse = null;

        //validator class, runs a check on the notedto
        List<ValidatorMessage> validate = ValidatorMessage.validate(notesDto);

        if (validate.isEmpty()) {

            //try to get a note with its id and user id
            Optional<Notes> findById = notesRepository.findByIdAndUserId(notesDto.getId(), userId);

            //check if a note was returned
            if (findById.isPresent()) {

                Notes note = findById.get();
                Notes returnNotes = notesDto.returnNotes();

                //retain old note details before the update occurs
                returnNotes.setId(note.getId());
                returnNotes.setUserId(note.getUserId());
                returnNotes.setCreateTime(note.getCreateTime());

                notesRepository.save(returnNotes);
                httpResponse = new ResponseObject(HttpStatus.OK, returnNotes)
                        .HttpResponse();
            } else {
                httpResponse = new ResponseObject(HttpStatus.NOT_FOUND,
                        new ValidatorMessage("id", "unknown note", null))
                        .HttpResponse();
            }

        } else {
            httpResponse = new ResponseObject(HttpStatus.BAD_REQUEST, validate).HttpResponse();
        }

        return httpResponse;
    }

    //TODO the deleteNotes method returns ResponseEntity which will be used to satisfied https requests
    public ResponseEntity<?> deleteNotes(int id, int userId) {

        ResponseEntity<?> httpResponse = null;

        Optional<Notes> notes = notesRepository.findByIdAndUserId(id, userId);

        if (notes.isPresent()) {
            notesRepository.deleteById(id);
            httpResponse = new ResponseObject(HttpStatus.OK, notes.get())
                    .HttpResponse();
        } else {
            httpResponse = new ResponseObject(HttpStatus.NOT_FOUND,
                    new ValidatorMessage("id", "unknown note", null))
                    .HttpResponse();
        }

        httpResponse = httpResponseNoteIfPresent(notes);

        return httpResponse;
    }

    //View note
    public ResponseEntity<?> viewNote(int id, Integer userId) {

        ResponseEntity<?> httpResponse = null;

        if (NoteUtils.notNull(userId)) {
            Optional<Notes> notes = notesRepository.findByIdAndUserId(id, userId);

            httpResponse = httpResponseNoteIfPresent(notes);

        } else {
            System.out.println("Not UserId");
            Optional<Notes> notes = notesRepository.findByIdAndVisibility(id,
                    NoteVisibility.PUBLIC);
            httpResponse = httpResponseNoteIfPresent(notes);
        }

        return httpResponse;
    }

    public ResponseEntity<?> fetchNotes(Integer id, Integer userId, String title, String body, String tags, String visibility,
            Integer page, Integer size, String orderColumn, String orderDirection) {

        ResponseEntity<?> httpResponse = null;

        //Implement pagination
        Pageable pageable = new Pagination(page, size, orderColumn, orderDirection).getPageable();

        //The specification implementation is used to filter notes.
        Specification<Notes> specification = new Specification<Notes>() {
            @Override
            public Predicate toPredicate(Root<Notes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return NoteUtils.getPredicates(id, userId, title, body, tags, visibility,
                        page, size, orderColumn, orderDirection, root, query, criteriaBuilder);
            }
        };

        List<Notes> content = notesRepository.findAll(specification, pageable).getContent();

        httpResponse = httpResponseNoteIfPresent(content);

        return httpResponse;
    }

    private ResponseEntity<?> httpResponseNoteIfPresent(Optional<Notes> notes) {
        ResponseEntity<?> httpResponse;
        if (notes.isPresent()) {
            System.out.println("Note Present: " + new Gson().toJson(notes.get()));
            httpResponse = new ResponseObject(HttpStatus.OK, notes.get())
                    .HttpResponse();
        } else {
            System.out.println("Note not present....");
            httpResponse = new ResponseObject(HttpStatus.NO_CONTENT,
                    new ValidatorMessage("id", "No result found", null))
                    .HttpResponse();
        }
        return httpResponse;
    }

    private ResponseEntity<?> httpResponseNoteIfPresent(List<Notes> notes) {
        ResponseEntity<?> httpResponse;
        if (NoteUtils.notEmpty(notes)) {
            httpResponse = new ResponseObject(HttpStatus.OK, notes)
                    .HttpResponse();
        } else {
            httpResponse = new ResponseObject(HttpStatus.NO_CONTENT,
                    new ValidatorMessage("id", "No result found", null))
                    .HttpResponse();
        }
        return httpResponse;
    }

}
