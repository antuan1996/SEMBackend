package com.khoubyari.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comment_table")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
    @Id
    @GeneratedValue()
    private long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name="author_id", updatable=false, insertable=false)
    private User author;

    @Column(name="author_id")
    private Long authorId;

    @ManyToOne
    @JoinColumn(name = "task_id", updatable=false, insertable=false)
    private Task task;

    @Column(name="task_id")
    private Long taskId;

    @Column
    private LocalDateTime createdDatetime;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreatedDatetime()
    {
        return createdDatetime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
