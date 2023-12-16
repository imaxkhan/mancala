package com.bol.mancala.domain.model.concept;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Act as pit concept in Mancala game
 */
@Getter
@Setter
public class Pit {
    //owner of the pit
    private Player player;
    //pit unique index
    private int index;
    //number of stones or seed in each pit
    private int seedCount;
    // is it a store(big pit)
    private boolean store;
}
