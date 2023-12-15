package com.bol.mancala.domain.model.concept;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Store {
    private UUID playerId;
    private int totalSeedCount;

    public Store(int totalSeedCount) {
        this.totalSeedCount = totalSeedCount;
    }
}
