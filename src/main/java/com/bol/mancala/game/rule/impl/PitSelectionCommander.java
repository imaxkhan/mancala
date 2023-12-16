package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;
import com.bol.mancala.game.rule.validator.PitValidator;

import java.util.UUID;

/**
 * second commander concrete class in chain
 * handling the state and validation of selected pit by player
 */
public class PitSelectionCommander implements GameCommander, PitValidator {

    @Override
    public void play(Game game, PlayDto playDto) throws CustomException {
        this.checkOwner(game, playDto.getPlayerId(), playDto.getPitIndex());
        this.checkStore(game,playDto.getPitIndex());
        this.checkCapacity(game, playDto.getPitIndex());
    }


    /**
     *
     * @param game entity
     * @param playerId of the game
     * @param pitIndex unique identifier of pit
     * @throws CustomException when player id of the pit
     * is different from the one who is picking the seed form pit
     */
    @Override
    public void checkOwner(Game game, UUID playerId, int pitIndex) throws CustomException {
        if (game.getBoard().getPits().get(pitIndex) != null) {
            Pit pit = game.getBoard().getPits().get(pitIndex);
            if (!playerId.equals(pit.getPlayer().getPlayerId())) {
                throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "Pit does not belong to you");
            }
        }

    }

    /**
     *
     * @param game entity
     * @param pitIndex unique identifier of pit
     * @throws CustomException when selected pit is empty of seeds
     */
    @Override
    public void checkCapacity(Game game, int pitIndex) throws CustomException {
        if (game.getBoard().getPits().get(pitIndex).getSeedCount() == 0) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "Pit is empty");
        }
    }

    /**
     *
     * @param game entity
     * @param pitIndex unique identifier of pit
     * @throws CustomException when selected pit is store (big pit)
     */
    @Override
    public void checkStore(Game game, int pitIndex) throws CustomException {
        if (game.getBoard().getPits().get(pitIndex).isStore()) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "First Move must be pit not store");
        }
    }
}
