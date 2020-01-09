package com.calendar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventFilter {
    private String header;
    private Date startTime;
    private Date endTime;
    private String description;
}
