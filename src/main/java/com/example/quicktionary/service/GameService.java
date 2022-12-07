package com.example.quicktionary.service;

import com.example.quicktionary.controller.WebSocketController;
import com.example.quicktionary.model.Room;
import com.example.quicktionary.model.Participant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Room room = Room.builder().build();
    private final WebSocketController webSocketController;

    public Room getRoom() {
        return room;
    }

    public void participantAcquiresCard(final String participantName,
                                        final String cardColor) {
        Participant participant = room.getParticipants().stream()
                .filter(p -> p.getName().equals(participantName))
                .findFirst().get();
        participant.setNumCardsAcquired(participant.getNumCardsAcquired() + 1);

        if ("YELLOW".equals(cardColor)) {
            room.updateYellowCardContent();
        } else if ("BLUE".equals(cardColor)) {
            room.updateBlueCardContent();
        } else if ("RED".equals(cardColor)) {
            room.updateRedCardContent();
        }

        room.setUpdateMessage(participantName + " acquired the " + cardColor + " card");
        webSocketController.roomUpdated(room);
    }
    public void clearRoom() {
        room.getParticipants().clear();
        room.setUpdateMessage("The host has reset room. Please go back to homepage");
        webSocketController.roomUpdated(room);
    }

    public synchronized Boolean createParticipant(final String participantName) throws GameServiceException {
        List<Participant> participants = room.getParticipants();
        Optional<Participant> participantOptional = participants.stream().filter(p -> p.getName().equals(participantName)).findFirst();
        if (participantOptional.isPresent()) {
            throw new GameServiceException("Room already contains participant with name=" + participantName);
        } else {
            Participant participant = Participant.builder().name(participantName).build();
            participants.add(participant);
            room.setUpdateMessage(participantName + " joined the room");
            webSocketController.roomUpdated(room);
            return true;
        }
    }

    public static class GameServiceException extends Exception {
        public GameServiceException(final String msg) {
            super(msg);
        }
    }
}
