package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.concept.Pit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PitResultDto {
    private int index;
    private UUID playerId;
    private int seedCount;
    private boolean store;

    public PitResultDto(Pit pit) {
        this.index = pit.getIndex();
        this.playerId = pit.getPlayerId();
        this.seedCount = pit.getSeedCount();
        this.store = pit.isStore();
    }
}
