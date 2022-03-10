package net.intelie.challenges;

import net.intelie.challenges.iterator.EventIteratorImpl;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventIteratorTest {

    @Autowired
    private EventRepository eventRepository;

    private Event event1;
    private Event event2;
    private EventIteratorImpl eventIterator;

    @Before
    public void loadEvents() {
        event1 = new Event(1, "type", 123);
        event2 = new Event(2, "type", 123);

        eventRepository.save(event1);
        eventRepository.save(event2);

        ArrayList<Event> eventList = eventRepository.findEvents("type", 100, 200);
        ArrayList<Long> eventIdList = new ArrayList<>();

        for (Event event: eventList) {
            eventIdList.add(event.getId());
        }

        eventIterator = new EventIteratorImpl(eventIdList, eventRepository);
    }

    @After
    public void clearEvents() {
        eventRepository.deleteAll();
        eventIterator = null;
    }

    @Test
    public void moveNextTest() throws Exception{
        assertTrue(eventIterator.moveNext());
    }

    @Test
    public void currentTest(){
        eventIterator.moveNext();
        Event event = eventIterator.current();

        assertTrue(compareEvents(event1, event));
    }

    @Test
    public void removeTest() {
        eventIterator.moveNext();
        eventIterator.moveNext();

        Event event = eventIterator.current();
        assertTrue(compareEvents(event2, event));

        eventIterator.remove();
        event = eventIterator.current();
        assertTrue(compareEvents(event1, event));
    }

    @Test
    public void closeTest() throws Exception {
        eventIterator.moveNext();
        eventIterator.close();

        assertFalse(eventIterator.moveNext());
    }

    private boolean compareEvents(Event event1, Event event2) {
        if(event1.getId() == event2.getId()
                && event1.getType() == event2.getType()
                && event1.getTimestamp() == event2.getTimestamp()){

            return true;
        }
        return false;
    }
}
