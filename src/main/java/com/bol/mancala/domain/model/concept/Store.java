package com.bol.mancala.domain.model.concept;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Store {
    private int totalSeedCount;

    public Store(int totalSeedCount) {
        this.totalSeedCount = totalSeedCount;
    }
}
