package com.khoubyari.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
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
        DONE
    }

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column
    private String description;

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
}

