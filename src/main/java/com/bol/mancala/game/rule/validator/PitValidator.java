package com.bol.mancala.game.rule.validator;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.model.game.Game;

import java.util.UUID;

public interface PitValidator {
    void checkOwner(Game game, UUID playerId, int pitIndex) throws CustomException;

    void checkCapacity(Game game, int pitIndex) throws CustomException;

    void checkStore(Game game, int pitIndex) throws CustomException;
}
