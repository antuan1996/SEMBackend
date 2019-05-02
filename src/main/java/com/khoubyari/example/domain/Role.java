package com.khoubyari.example.domain;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private String name;

    private String representation;

}
