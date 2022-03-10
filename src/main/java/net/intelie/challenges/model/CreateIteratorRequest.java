package net.intelie.challenges.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor

@ToString
public class CreateIteratorRequest {

    private String type;
    private long startTime;
    private long endTime;
}
