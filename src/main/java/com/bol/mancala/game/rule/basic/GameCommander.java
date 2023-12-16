package com.bol.mancala.game.rule.basic;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.game.Game;

/**
 * Commander common interface
 * being implemented by all responsible classes
 */
public interface GameCommander {
    /**
     *
     * @param game entity being passed to this method
     * @param playDto as request object being passed to this method
     * @throws CustomException being thrown from any part of the command chains
     */
    void play(Game game,PlayDto playDto) throws CustomException;
}
