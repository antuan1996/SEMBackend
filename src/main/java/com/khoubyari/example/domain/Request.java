package com.khoubyari.example.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@Table(name = "request_table")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
    @Id
    @GeneratedValue()
    private long id;

    @Column()
    private String title;

    @Column()
    private String description;

    @Column()
    private LocalDate expectedDueDate;

    @Email
    @Column()
    private String contactEmail;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpectedDueDate() {
        return expectedDueDate;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
