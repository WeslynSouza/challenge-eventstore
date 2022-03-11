package net.intelie.challenges.iterator;

import lombok.NoArgsConstructor;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.repository.EventRepository;

import java.util.ArrayList;

@NoArgsConstructor
public class EventIteratorImpl implements EventIterator{

    /**
     *  I decided to create the iterator based on a list of ids, which will be
     *  used to reference the events, reducing the amount of information used
     *  in the iterator, aiming at a performance gain in the possibility of the
     *  project being scalable.
     */

    private ArrayList<Long> eventIdList;
    private EventRepository eventRepository;

    /**
     * Variable that defines what is the current position of the iterator.
     */
    private int position = -1;

    /**
     * Variable that validates if the first positioning movement of the iterator
     * has already occurred.
     */
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

    /**
     * Fetch the event using the id pointed to in the iterator and return it.
     * @return null in case of exception.
     */
    @Override
    public Event current() {
        try{
            if (!moveNextWasCalled) {
                throw new IllegalStateException("Event not found, use the method MoveNext first.");
            } else if (position >= eventIdList.size()) {
                throw new IllegalStateException("The list is over.");
            }

            Event current = eventRepository.findEventById(eventIdList.get(position));

            return current;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Removes the event from the database and the list id present in the iterator,
     * then returns the positioning to the previous value.
     */
    @Override
    public void remove() {
        try{
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Clears the iterator's list of items.
     */
    @Override
    public void close() throws Exception {
        eventIdList = new ArrayList<>();
        System.out.println("Closing iterator.");
    }
}
