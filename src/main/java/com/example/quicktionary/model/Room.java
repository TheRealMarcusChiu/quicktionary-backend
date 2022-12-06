package com.example.quicktionary.model;

import com.example.quicktionary.service.GameData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Room {

    private static final Random RANDOM = new Random();

    private List<Participant> participants = new ArrayList<>();

    private String activeYellowCardContent;
    private String activeBlueCardContent;
    private String activeRedCardContent;

    public Room() {
        updateYellowCardContent();
        updateBlueCardContent();
        updateRedCardContent();
    }

    public static Room.RoomBuilder builder() {
        return new Room().toBuilder();
    }

    public void updateYellowCardContent() {
        this.activeYellowCardContent = getRandom(GameData.YELLOW_CARD_CONTENTS);
    }

    public void updateBlueCardContent() {
        this.activeBlueCardContent = getRandom(GameData.BLUE_CARD_CONTENTS);
    }

    public void updateRedCardContent() {
        this.activeRedCardContent = getRandom(GameData.RED_CARD_CONTENTS);
    }

    private String getRandom(final List<String> contents) {
        int randomIndex = RANDOM.nextInt(contents.size());
        return contents.get(randomIndex);
    }
}
