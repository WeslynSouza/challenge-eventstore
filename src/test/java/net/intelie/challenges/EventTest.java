package net.intelie.challenges;

import net.intelie.challenges.model.Event;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event( 2,"some_type", 123L);

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
        assertEquals(2, event.getId());
        assertEquals(123L, event.getTimestamp());
        assertEquals("some_type", event.getType());
    }

}