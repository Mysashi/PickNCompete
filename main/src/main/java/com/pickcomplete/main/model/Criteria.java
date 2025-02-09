package com.pickcomplete.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "criteria")
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int fromInt;
    private int toInt;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getFromInt() {
        return fromInt;
    }

    public void setFromInt(int fromInt) {
        this.fromInt = fromInt;
    }

    @ManyToOne
    @JoinColumn(name = "criteriaId")
    private Event event;


    public int getToInt() {
        return toInt;
    }

    public void setToInt(int to) {
        this.toInt = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrom() {
        return fromInt;
    }

    public void setFrom(int from) {
        this.fromInt = from;
    }

}
