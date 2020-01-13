package com.calendar.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENT")
@Getter
@Setter
@ToString
public class Event {
    @Id
    @SequenceGenerator(name = "EVENT_SEQ",sequenceName = "EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "DESCRIPTION")
    private String description;
}
