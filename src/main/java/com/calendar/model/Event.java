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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!id.equals(event.id)) return false;
        if (!header.equals(event.header)) return false;
        return description.equals(event.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + header.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
