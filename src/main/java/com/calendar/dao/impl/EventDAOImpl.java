package com.calendar.dao.impl;

import com.calendar.dao.EventDAO;
import com.calendar.model.Event;
import com.calendar.model.EventFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDAOImpl extends BaseDAOImpl<Event> implements EventDAO {
    public EventDAOImpl() {
        super(Event.class);
    }

    @Override
    public List<Event> getEventsByFilter(EventFilter filter) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = cb.createQuery(Event.class);
        Root<Event> root = criteriaQuery.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getHeader() != null) {
            predicates.add(cb.like(root.get("header"), "%" + filter.getHeader() + "%"));
        }
        if (filter.getDescription() != null) {
            predicates.add(cb.like(root.get("description"), "%" + filter.getDescription() + "%"));
        }
        if (filter.getStartTime() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("startTime"), (Comparable) new SimpleDateFormat("yyyy-MM-dd").parse(filter.getStartTime())));
        }
        if (filter.getEndTime() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("endTime"), (Comparable) new SimpleDateFormat("yyyy-MM-dd").parse(filter.getEndTime())));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(cb.desc(root.get("startTime")));;

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
