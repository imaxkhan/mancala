package com.bol.mancala.domain.model.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Champion {
    private UUID playerId;

    public Champion(UUID playerId) {
        this.playerId = playerId;
    }
}
