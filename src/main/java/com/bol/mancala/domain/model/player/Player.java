package com.bol.mancala.domain.model.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Player {
    private UUID playerId;
    private String userName;
}
