package com.khoubyari.example.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "order_table")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    public enum OrderStatus {
        CONFIRMED,
        INPROGRESS,
        DONE,
        CANCELLED
    }

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Email
    @Column
    private String customerEmail;

    @Column()
    private LocalDate expectedDueDate;

    @Column
    private LocalDateTime createdDatetime;

    @Column
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedDatetime() {
        return createdDatetime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getExpectedDueDate() {
        if(expectedDueDate == null){
            return null;
        }
        return expectedDueDate.format(DateTimeFormatter.ISO_DATE);
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}

