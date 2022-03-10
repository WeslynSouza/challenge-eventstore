package net.intelie.challenges;

import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class dataLoader {

    private EventRepository eventRepository;

    @Autowired
    public dataLoader(EventRepository eventRepository) {
        this.eventRepository  = eventRepository;

        this.loader();
    }

    private void loader() {
        ArrayList<Event> eventList = new ArrayList<>();

        eventList.add(new Event(1, "some_type", 100));
        eventList.add(new Event(2, "some_type", 180));
        eventList.add(new Event(3, "some_type", 120));
        eventList.add(new Event(4, "some_type", 149));

        eventList.add(new Event(5, "type", 290));
        eventList.add(new Event(6, "type", 123));
        eventList.add(new Event(7, "type", 193));
        eventList.add(new Event(8, "type", 200));

        eventList.forEach(event -> {
            eventRepository.save(event);
        });
    }

}
