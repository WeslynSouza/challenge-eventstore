package net.intelie.challenges.iterator;

import lombok.NoArgsConstructor;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;

import java.util.ArrayList;

@NoArgsConstructor
public class EventIteratorImpl implements EventIterator{

    private ArrayList<Long> eventIdList;
    private EventRepository eventRepository;
    private int position = -1;
    private boolean moveNextWasCalled = false;

    public EventIteratorImpl(ArrayList<Long> eventIdList, EventRepository eventRepository) {

        this.eventIdList = eventIdList;
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean moveNext() {
        if(this.position < eventIdList.size() - 1){
            this.position++;

            moveNextWasCalled = true;

            return true;
        }

        return false;
    }

    @Override
    public Event current() {
        if (!moveNextWasCalled) {
            throw new IllegalStateException("Event not found, use the method MoveNext first.");
        } else if (position >= eventIdList.size()) {
            throw new IllegalStateException("The list is over.");
        }

       Event current = eventRepository.findEventById(eventIdList.get(position));

        return current;
    }

    @Override
    public void remove() {
        if (!moveNextWasCalled) {
            throw new IllegalStateException("Event not found, use the method MoveNext first.");
        } else if (position >= eventIdList.size()) {
            throw new IllegalStateException("The list is over.");
        }

        if(position != 0){
            position--;
        }

        eventRepository.deleteById(eventIdList.get(position));
        eventIdList.remove(position);
    }

    @Override
    public void close() throws Exception {
        eventIdList = new ArrayList<>();
        System.out.println("Closing iterator.");
    }
}
