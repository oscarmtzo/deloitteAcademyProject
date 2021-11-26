package com.example.deloitteAcademy.deloitteAcademy.entity;

import javax.persistence.*;
import java.io.Serializable;

//Adding annotations for Spring Data JPA to persist the data
@Table(name = "tour_package")
@Entity
public class TourPackage implements Serializable {
    //
    @Id
    private String code;

    @Column
    private String name;

    protected TourPackage () {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TourPackage{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

