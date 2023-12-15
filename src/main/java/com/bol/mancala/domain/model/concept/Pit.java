package com.bol.mancala.domain.model.concept;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Pit {
    private UUID playerId;
    private int index;
    private int seedCount;
    private boolean store;
}