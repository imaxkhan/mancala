package com.bol.mancala.domain.model.concept;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Pit {
    private Player player;
    private int index;
    private int seedCount;
    private boolean store;
}
