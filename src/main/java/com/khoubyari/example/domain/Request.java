package com.khoubyari.example.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Column(columnDefinition="TEXT")
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

    public String getExpectedDueDate() {
        return expectedDueDate.format(DateTimeFormatter.ISO_DATE);
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
