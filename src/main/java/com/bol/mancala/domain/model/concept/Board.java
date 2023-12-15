package com.bol.mancala.domain.model.concept;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Board {
    private List<Pit> pits;
    private UUID activePlayerId;
}
