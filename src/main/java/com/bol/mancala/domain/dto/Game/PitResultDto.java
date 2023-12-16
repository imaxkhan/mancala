package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.concept.Pit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Response DTO as mediator showing status of pits
 * after play and gameSetup creation
 */
@Getter
@Setter
@NoArgsConstructor
public class PitResultDto {
    private int index;
    private PlayerResultDto player;
    private int seedCount;
    private boolean store;

    public PitResultDto(Pit pit) {
        this.index = pit.getIndex();
        this.player = new PlayerResultDto(pit.getPlayer());
        this.seedCount = pit.getSeedCount();
        this.store = pit.isStore();
    }
}
