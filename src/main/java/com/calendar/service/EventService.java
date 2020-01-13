package com.calendar.service;

import com.calendar.dao.EventDAO;
import com.calendar.exception.BadRequestException;
import com.calendar.exception.NotFoundException;
import com.calendar.model.Event;
import com.calendar.model.EventFilter;
import com.calendar.util.ParserUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventDAO eventDAO;
    private ParserUtil parserUtil;

    public EventService(EventDAO eventDAO, ParserUtil parserUtil) {
        this.eventDAO = eventDAO;
        this.parserUtil = parserUtil;
    }

    public Event save(Event event) {
        validate(event);

        return eventDAO.save(event);
    }

    public Event update(Event event) {
        validate(event);

        return eventDAO.update(event);
    }

    public void delete(String id) {
        Event event = findById(parserUtil.parseId(id));
        eventDAO.delete(event);
    }

    public Event getEvent(String id) {
        return  findById(parserUtil.parseId(id));
    }

    public List<Event> getEventsByFilter(EventFilter filter) throws Exception {
        return eventDAO.getEventsByFilter(filter);
    }

    private Event findById(long id) throws NotFoundException {
        Event event = eventDAO.findById(id);
        if (event == null) {
            throw new NotFoundException("Error: event(id: " + id + ") was not found");
        }

        return event;
    }

    private void validate(Event event) throws BadRequestException {
        if (event.getHeader().length() == 0) {
            throw new BadRequestException("Error: header of the event cannot be empty.");
        }

        if (event.getHeader().length() > 200) {
            throw new BadRequestException("Error: header of the event cannot be more than 200 symbols.");
        }

        if (event.getDescription().length() == 0) {
            throw new BadRequestException("Error: description of the event cannot be empty.");
        }

        if (event.getDescription().length() > 500) {
            throw new BadRequestException("Error: description of the event cannot be more than 500 symbols.");
        }

        if (event.getStartTime() == null) {
            throw new BadRequestException("Error: start time of the event cannot be null.");
        }

        if (event.getEndTime() == null) {
            throw new BadRequestException("Error: end time of the event cannot be null.");
        }

        if (event.getEndTime().before(event.getStartTime())) {
            throw new BadRequestException("Error: end time of the event cannot be before start time");
        }
    }
}
