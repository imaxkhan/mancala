package com.bol.mancala.game.rule.validator;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;

import java.util.UUID;

public interface GameValidator {
    void checkGameStatus(GameStatus status) throws CustomException;
    void checkTurn(Board board, UUID playerId) throws CustomException;
}
