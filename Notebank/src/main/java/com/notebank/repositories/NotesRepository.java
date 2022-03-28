/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.repositories;

import com.notebank.models.Notes;
import com.notebank.models.enums.NoteVisibility;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Uche Powers
 */
@Repository
public interface NotesRepository extends CrudRepository<Notes, Integer>, PagingAndSortingRepository<Notes, Integer>, JpaSpecificationExecutor<Notes>, JpaRepository<Notes, Integer> {

    public Optional<Notes> findByIdAndUserId(int id, int userId);

    public Optional<Notes> findByIdAndVisibility(int id, NoteVisibility visibility);

}
