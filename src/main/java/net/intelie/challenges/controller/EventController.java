package net.intelie.challenges.controller;

import net.intelie.challenges.iterator.EventIterator;
import net.intelie.challenges.model.CreateIteratorRequest;
import net.intelie.challenges.model.Event;
import net.intelie.challenges.service.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private EventStore eventStore;

    private EventIterator eventIterator;

    @PostMapping("/InsertEvent")
    public Event insertEvent(@RequestBody Event event) {

        eventStore.insert(event);
        return event;
    }

    @DeleteMapping("/RemoveAllEventByType")
    public void removeAllByType(@RequestParam String type) {

        eventStore.removeAll(type);
    }

    @PostMapping("/CreateIterator")
    public void createIterator(@RequestBody CreateIteratorRequest req) {

        this.eventIterator = eventStore.query(req.getType(), req.getStartTime(), req.getEndTime());
    }

    @GetMapping("/MoveIterator")
    public boolean moveIterator(){

        return this.eventIterator.moveNext();
    }

    @GetMapping("/CurrentIterator")
    public Event currentIterator(){

        return this.eventIterator.current();
    }

    @DeleteMapping("/RemoveCurrentIterator")
    public void removeCurrentIterator() {

        this.eventIterator.remove();
    }

    @GetMapping("/CloseIterator")
    public void closeIterator() throws Exception {

        this.eventIterator.close();
    }
}
