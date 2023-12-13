package com.bol.mancala.domain.model.game;

import lombok.Getter;

@Getter
public class GameSetting {
    private int totalPits = 12;
    private int totalStores = 2;
    private int totalSeedPerPit = 6;
    private final int totalSeeds;
    private int playerCount = 2;

    public GameSetting(int totalPits, int totalStores, int totalSeedPerPit, int playerCount) {
        this.totalPits = totalPits;
        this.totalStores = totalStores;
        this.totalSeedPerPit = totalSeedPerPit;
        this.totalSeeds = totalPits * totalSeedPerPit;
        this.playerCount = playerCount;
    }
}
