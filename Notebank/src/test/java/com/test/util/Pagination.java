/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.util;

import com.notebank.utils.NoteUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author uchephilz
 */
public class Pagination {

    private Integer page;
    private Integer size;
    private String orderColumn;
    private String orderDirection;

    public Pagination(Integer page, Integer size, String orderColumn, String orderDirection) {
        this.page = page;
        this.size = size;
        this.orderColumn = orderColumn;
        this.orderDirection = orderDirection;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Pageable getPageable() {
        PageRequest pr = null;
        if (NoteUtils.notNull(this.size) && NoteUtils.notNull(this.page)) {

            if (NoteUtils.notEmpty(this.orderColumn) && NoteUtils.notEmpty(this.orderDirection)) {
                pr = PageRequest.of(this.page,
                        this.size,
                        Sort.by(Sort.Direction.DESC, this.orderColumn));
            } else {
                pr = PageRequest.of(this.page, this.size);
            }

        } else {
            pr = PageRequest.of(0, 10);
        }

        return pr;
    }

}
