package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;

/**
 * Fifth and final Commander concrete class in chain
 * Responsible for closing the game
 */
public class FinishCommander implements GameCommander {

    // set the game status to finish that won't be accessible for play anymore
    @Override
    public void play(Game game, PlayDto playDto) {
        if (game.getChampion() != null && game.getChampion().getPlayer() != null) {
            game.setStatus(GameStatus.FINISHED);
        }
    }
}
