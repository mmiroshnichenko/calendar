package com.calendar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventFilter {
    private String header;
    private String startTime;
    private String endTime;
    private String description;
}
