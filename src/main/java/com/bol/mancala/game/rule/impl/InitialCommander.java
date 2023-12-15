package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;
import com.bol.mancala.game.rule.validator.GameValidator;

import java.util.UUID;

public class InitialCommander implements GameCommander, GameValidator {

    @Override
    public void play(Game game, PlayDto playDto) throws CustomException {
        checkGameStatus(game.getStatus());
    }

    @Override
    public void checkGameStatus(GameStatus status) throws CustomException {
        if (status.equals(GameStatus.NOT_STARTED)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "game setup not fully done");
        } else if (status.equals(GameStatus.FINISHED)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "sorry, game is finished");
        }
    }

    @Override
    public void checkTurn(Board board, UUID playerId) throws CustomException {
        if (!board.getActivePlayerId().equals(playerId)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "its not your turn");
        }
    }
}
