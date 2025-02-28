package com.pickcomplete.main.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventsName() {
        return eventsName;
    }

    public void setEventsName(String eventsName) {
        this.eventsName = eventsName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getJuryNum() {
        return juryNum;
    }

    public void setJuryNum(int juryNum) {
        this.juryNum = juryNum;
    }

    public String getLinkingCode() {
        return linkingCode;
    }

    public void setLinkingCode(String linkingCode) {
        this.linkingCode = linkingCode;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventId;
    private String eventsName;
    private String username;
    private String password;
    private int teamNum;
    private int juryNum;

    public Event() {
    }



    private String linkingCode;



    public Event(String eventsName, String username, String password, int teamNum, int juryNum, String linkingCode, String juryCode) {
        this.eventsName = eventsName;
        this.username = username;
        this.password = password;
        this.teamNum = teamNum;
        this.juryNum = juryNum;
        this.linkingCode = linkingCode;
        this.juryCode = juryCode;
    }

    private String juryCode;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Criteria> criteria = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jury> jury = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jury> team = new ArrayList<>();

    public String getJuryCode() {
        return juryCode;
    }

    public void setJuryCode(String juryCode) {
        this.juryCode = juryCode;
    }
}
