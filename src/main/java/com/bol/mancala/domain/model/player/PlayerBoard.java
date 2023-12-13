package com.bol.mancala.domain.model.player;

import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.concept.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PlayerBoard {
    private Player player;
    private Set<Pit> pits;
    private Store store;
    private boolean myTurn;
}
