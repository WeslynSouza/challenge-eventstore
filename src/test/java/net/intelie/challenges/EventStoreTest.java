package net.intelie.challenges;

import net.intelie.challenges.iterator.EventIteratorImpl;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;
import net.intelie.challenges.service.EventStore;
import net.intelie.challenges.service.EventStoreImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventStoreTest {

    @TestConfiguration
    static class EventStoreTestConfiguration {

        @Bean
        public EventStore eventStore() {
            return new EventStoreImpl();
        }
    }

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventStore eventStore;

    private Event event1;
    private Event event2;

    @Before
    public void loadEvents() {
        event1 = new Event(1, "type", 123);
        event2 = new Event(2, "type", 123);

        eventStore.insert(event1);
        eventStore.insert(event2);
    }

    @After
    public void clearEvents() {
        eventRepository.deleteAll();
    }

    @Test
    public void insertTest() {
        eventStore.insert(new Event(3, "some_type", 123));

        assertNotNull(eventRepository.findEventById(3));
    }

    @Test
    public void removeAllTest() {
        eventStore.removeAll("type");

        assertTrue(eventRepository.findEventsByType("type").isEmpty());
    }

    @Test
    public void queryTest() {
        EventIteratorImpl eventIterator = (EventIteratorImpl) eventStore.query("type", 100, 200);

        assertTrue(eventIterator.moveNext());
    }
}
