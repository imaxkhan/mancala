package com.bol.mancala.domain.model.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An entity for showing the winner of the game
 */
@Getter
@Setter
@NoArgsConstructor
public class Champion {
    private Player player;
    private int totalSeed;

    public Champion(Player player, int totalSeed) {
        this.player = player;
        this.totalSeed = totalSeed;
    }
}
