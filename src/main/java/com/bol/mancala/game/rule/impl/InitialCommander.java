package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;
import com.bol.mancala.game.rule.validator.GameValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * First Commander concrete class in chain
 */
@Slf4j
public class InitialCommander implements GameCommander, GameValidator {

    @Override
    public void action(Game game, PlayDto playDto) throws CustomException {
        checkGameStatus(game.getStatus());
        checkTurn(game.getBoard(), playDto.getPlayerId());
        log.info("Player: " + playDto.getPlayerId() + " selected pit with index: " + playDto.getPitIndex());
    }

    /**
     * @param status as input
     * @throws CustomException on game not in running state
     */
    @Override
    public void checkGameStatus(GameStatus status) throws CustomException {
        if (status.equals(GameStatus.NOT_STARTED)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "game setup not fully done");
        } else if (status.equals(GameStatus.FINISHED)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "sorry, game is finished");
        }
    }

    /**
     * @param board    of the game
     * @param playerId of picking the pit in his/her turn
     * @throws CustomException when player violate his/her turn
     */
    @Override
    public void checkTurn(Board board, UUID playerId) throws CustomException {
        if (!board.getActivePlayer().getPlayerId().equals(playerId)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "its not your turn");
        }
    }
}
