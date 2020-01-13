package com.calendar.dao;

import com.calendar.model.Event;
import com.calendar.model.EventFilter;

import java.util.List;

public interface EventDAO extends BaseDAO<Event> {
    List<Event> getEventsByFilter(EventFilter filter) throws Exception;
}
