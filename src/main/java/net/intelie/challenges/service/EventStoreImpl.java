package net.intelie.challenges.service;

import lombok.AllArgsConstructor;
import net.intelie.challenges.iterator.EventIterator;
import net.intelie.challenges.iterator.EventIteratorImpl;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventStoreImpl implements EventStore{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void insert(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void removeAll(String type) {
        eventRepository.removeAllByType(type);
    }

    @Override
   public EventIterator query(String type, long startTime, long endTime){
        ArrayList<Event> eventList = eventRepository.findEvents(type, startTime, endTime);
        ArrayList<Long> eventIdList = new ArrayList<>();

        for (Event event: eventList) {
            eventIdList.add(event.getId());
        }

        EventIterator eventIterator = new EventIteratorImpl(eventIdList, eventRepository);

        return eventIterator;
   }

}
