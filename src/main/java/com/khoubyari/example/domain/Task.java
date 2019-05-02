package com.khoubyari.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "task")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    public enum TaskStatus {
        TODO,
        INPROGRESS,
        CHECKING,
        DONE
    }

    @Id
    @GeneratedValue()
    private long id;

    @Column()
    private String title;

    @Column()
    private String description;

    @Column()
    private LocalDate createdDate;

    @Column
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "assigned_person_id", updatable=false, insertable=false)
    private User assignedPerson;

    @Column(name="assigned_person_id")
    private Long assignedPersonId;

    @ManyToOne
    @JoinColumn(name = "creator_id", updatable=false, insertable=false)
    private User creator;

    @Column(name="creator_id")
    private Long creatorId;

    @OneToMany
    private Set<Comment> commentSet;

    @ManyToOne
    @JoinColumn(name="order_id", updatable=false, insertable=false)
    private Order order;

    @Column(name="order_id")
    private Long orderId;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public Long getAssignedPersonId() {
        return assignedPersonId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
