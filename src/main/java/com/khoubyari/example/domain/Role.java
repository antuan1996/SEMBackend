package com.khoubyari.example.domain;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private String name;

    @Column
    private String representation;

    public void setName(String name) {
        this.name = name;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
