/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.repositories;

import com.notebank.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Uche Powers
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

//    @Query(value = "select count(*) = 0 FROM users t WHERE t.email = :email", nativeQuery = true)
//    boolean emailDoNotExists(String email);

    public Optional<Users> findByEmail(String email);

    public Optional<Users> findByAccessToken(String accesssToken);

}
