/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notebank.models;

import com.notebank.models.enums.Visbility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Entity
@Table
public class Notes implements Serializable {

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
    private Visbility visibility;
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Basic(optional = true)
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts = new Date();

    public Notes(Integer id) {
        this.id = id;
    }

}
