package com.example.quicktionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private String name;
    private Integer numCardsAcquired = 0;
    private OffsetDateTime dateCreated = OffsetDateTime.now();

    public static Participant.ParticipantBuilder builder() {
        return new Participant().toBuilder();
    }
}
