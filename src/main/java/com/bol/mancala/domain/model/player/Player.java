package com.bol.mancala.domain.model.player;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player {
    private UUID playerId;
    private String userName;
    private PlayerBoard playerBoard;
}
