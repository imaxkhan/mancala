package com.bol.mancala.game.rule.basic.container;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LastPitContainerInfo {
    private int currentIndex;
    private int oldSeedCount;

    public LastPitContainerInfo(int currentIndex, int oldSeedCount) {
        this.currentIndex = currentIndex;
        this.oldSeedCount = oldSeedCount;
    }
}
