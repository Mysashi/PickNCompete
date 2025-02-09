package com.pickcomplete.main.model;


import jakarta.persistence.*;

@Entity
@Table(name = "jury")
public class Jury {
    @Id
    private long Id;

    private String name;
    private String marks;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "juryId")
    private Event event;
}
