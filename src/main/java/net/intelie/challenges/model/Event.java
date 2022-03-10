package net.intelie.challenges.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is just an event stub, feel free to expand it if needed.
 */

@Getter
@AllArgsConstructor

@Entity
@Table(name = "Event")
public class Event {

    @Id
    private long id;

    private String type;
    private long timestamp;

    public Event() { }
}
