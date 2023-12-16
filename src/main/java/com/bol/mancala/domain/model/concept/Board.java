package com.bol.mancala.domain.model.concept;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * An entity class in real world
 * including physical game concepts
 */
@Getter
@Setter
public class Board {
    //list of pits
    private List<Pit> pits;
    //handle player turn each time
    private Player activePlayer;
}
