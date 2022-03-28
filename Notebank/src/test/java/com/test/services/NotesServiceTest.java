/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.services;

import com.notebank.models.Notes;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.notebank.models.dto.NotesDto;
import com.notebank.models.enums.NoteVisibility;
import com.notebank.repositories.NotesRepository;
import com.notebank.services.NotesService;
import com.notebank.utils.NoteUtils;
import com.notebank.utils.Pagination;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Uche Powers
 */
@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private NotesRepository notesRepository;

    @Autowired
    private NotesService notesService;

    NotesDto notesDto;

    Notes notes;

    List<Notes> noteList = new ArrayList<Notes>();

    @BeforeEach
    void setUp() {
        this.notesService = new NotesService(this.notesRepository);

        this.notesDto = new NotesDto("Title", "Body of text", "uche,Mark", NoteVisibility.PUBLIC);

        this.notes = new Notes(3, "Title", "Body of text", "uche, mark", NoteVisibility.PUBLIC, 2);

    }

    @DisplayName("Test create successfull note")
    @Test
    void createNotes() {

        
        ResponseEntity<?> createNotes = notesService.createNotes(notesDto, 2);

        assertThat(createNotes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Test successfull update note")
    void updateNotes() {

        //create mocks for data needed for updateNotes
        notesDto.setId(3);
        Mockito.when(notesRepository.findByIdAndUserId(3, 2)).thenReturn(Optional.of(notes));
        
        ResponseEntity<?> updateNotes = notesService.updateNotes(notesDto, 2);
        assertThat(updateNotes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Test update note id not found")
    void updateNotesIdNotFound() {

        //create mocks for data
        notesDto.setId(2);
        Mockito.lenient().when(notesRepository.findByIdAndUserId(3, 2)).thenReturn(Optional.of(notes));

        ResponseEntity<?> updateNotes = notesService.updateNotes(notesDto, 2);
        assertThat(updateNotes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Test delete note")
    void deleteNotes() {

        //create mocks for data that deleteNotes needs
        notesDto.setId(3);
        Mockito.when(notesRepository.findByIdAndUserId(3, 2)).thenReturn(Optional.of(notes));

        ResponseEntity<?> deleteNotes = notesService.deleteNotes(notesDto.getId(), notes.getUserId());

        assertThat(deleteNotes.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Test view note")
    void viewNote() {

        //create mocks for data that viewNote needs
        Mockito.lenient().when(notesRepository.findByIdAndUserId(3, 2)).thenReturn(Optional.of(notes));
        Mockito.lenient().when(notesRepository.findByIdAndVisibility(3, NoteVisibility.PUBLIC)).thenReturn(Optional.of(notes));

        ResponseEntity<?> viewNote = notesService.viewNote(3, 2);

        assertThat(viewNote.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
