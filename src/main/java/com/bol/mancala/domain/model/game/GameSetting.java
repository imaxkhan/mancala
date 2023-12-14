package com.bol.mancala.domain.model.game;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSetting {
    private int totalPits;
    private int totalStores;
    private int totalSeedPerPit;
    private final int totalSeeds;
    private int playerCount;
    private int totalPitPerPlayer;
}
