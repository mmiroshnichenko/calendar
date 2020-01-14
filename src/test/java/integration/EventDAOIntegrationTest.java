package integration;

import com.calendar.Application;
import com.calendar.dao.EventDAO;
import com.calendar.model.Event;
import com.calendar.model.EventFilter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDAOIntegrationTest {

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void test1_SaveEvent() {
        Event event = new Event();
        event.setHeader("test header");
        event.setDescription("test description");
        event.setStartTime(new Date());
        event.setEndTime(new Date());

        eventDAO.save(event);

        Event event1 = eventDAO.findById(1);
        Assert.assertEquals(event, event1);
    }

    @Test
    public void test2_UpdateEvent() {
        Event event = eventDAO.findById(1);
        event.setDescription("new description");
        event.setHeader("new header");
        eventDAO.update(event);

        Event event1 = eventDAO.findById(1);
        Assert.assertEquals(event1.getDescription(), "new description");
        Assert.assertEquals(event1.getHeader(), "new header");
    }

    @Test
    public void test3_GetEvent() {
        Event event = eventDAO.findById(100);
        Assert.assertNull(event);

        event = eventDAO.findById(1);
        Assert.assertNotNull(event);
    }

    @Test
    @Transactional
    public void test4_DeleteEvent() {
        Event event = eventDAO.findById(1);
        Assert.assertNotNull(event);
        eventDAO.delete(event);

        Assert.assertNull(eventDAO.findById(1));
    }

    @Test
    public void test5_FindEvent() {
        try {
            Event event = new Event();
            event.setHeader("test header2");
            event.setDescription("test description2");
            event.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-10"));
            event.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-10"));
            eventDAO.save(event);

            EventFilter filter = new EventFilter();
            filter.setDescription("descr");
            filter.setHeader("head");
            filter.setStartTime("2020-01-09");
            filter.setEndTime("2020-01-12");

            Assert.assertEquals(eventDAO.getEventsByFilter(filter).size(), 1);
        } catch (Exception e) {

        }

    }
}
