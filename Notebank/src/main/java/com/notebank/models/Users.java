/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notebank.models;

import com.notebank.models.dto.UserDto;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

import lombok.*;

/**
 *
 * @author Uche Powers
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
public class Users implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "password", nullable = false)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "access_token", nullable = false, length = 500)
    private String accessToken;
    @Basic(optional = true)
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts = new Date();

    public Users(Integer id) {
        this.id = id;
    }

    public Users(String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }


}
