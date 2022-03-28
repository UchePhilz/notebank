/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.models.dto;

import com.notebank.models.Notes;
import com.notebank.models.enums.NoteVisibility;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Uche Powers
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesDto {

    @Id
    @GeneratedValue
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "body", nullable = false, length = 10000)
    private String body;
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tag", nullable = false, length = 200)
    private String tag;
    @Column(name = "visibility")
    private NoteVisibility visibility;

    public NotesDto(String title, String body, String tag, NoteVisibility visibility) {
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.visibility = visibility;
    }

    public Notes returnNotes() {
        return new Notes(title, body, tag, visibility);
    }

}
