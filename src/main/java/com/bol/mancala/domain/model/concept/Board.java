package com.bol.mancala.domain.model.concept;

import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Board {
    private List<Pit> pits;
    private Player activePlayer;
}
