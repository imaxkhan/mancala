package com.bol.mancala.domain.model.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * An entity for players involved in game
 */
@Getter
@Setter
@Builder
public class Player {
    //unique player id in each game
    private UUID playerId;
    //name or title for player
    private String userName;
}
