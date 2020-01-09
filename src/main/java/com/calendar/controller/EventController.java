package com.calendar.controller;

import com.calendar.model.Event;
import com.calendar.model.EventFilter;
import com.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public Event save(@RequestBody Event event) throws Exception {
        return event;
    }

    @PutMapping("/event")
    public Event update(@RequestBody Event event) throws Exception {
        return event;
    }

    @DeleteMapping("/event/{eventId}")
    public void delete(@PathVariable String eventId) throws Exception {

    }

    @GetMapping("/event/{eventId}")
    public void getEvent(@PathVariable String eventId) throws Exception {

    }

    @GetMapping("/event/find")
    public List<Event> findEvents(EventFilter eventFilter) throws Exception {
        return null;
    }

}
