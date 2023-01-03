package com.example.quicktionary.controller;

import com.example.quicktionary.controller.model.Response;
import com.example.quicktionary.model.Room;
import com.example.quicktionary.service.GameService;
import com.example.quicktionary.service.GameService.GameServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
public class DefaultController {

    private final GameService gameService;

    @GetMapping("/room")
    public Room getRoom() {
        return gameService.getRoom();
    }

    @GetMapping("/room/clear")
    public Room clearRoom() {
        gameService.clearRoom();
        return gameService.getRoom();
    }

    @PostMapping("/add/participant/{name}")
    public Response addParticipant(@PathVariable("name") String participantName) {
        try {
            gameService.createParticipant(participantName);
            return Response.success();
        } catch (GameServiceException e) {
            return Response.fail(e.getMessage());
        }
    }

    @PostMapping("/participant/{name}/acquires/{card-color}")
    public Response participantAcquiresCard(final @PathVariable("name") String participantName,
                                            final @PathVariable("card-color") String cardColor) {
        gameService.participantAcquiresCard(participantName, cardColor);
        return Response.success();
    }

    @PostMapping("/room/clear-current-cards")
    public Response clearCurrentCards(final @PathVariable("name") String participantName) {
        gameService.clearCurrentCards(participantName);
        return Response.success();
    }
}
