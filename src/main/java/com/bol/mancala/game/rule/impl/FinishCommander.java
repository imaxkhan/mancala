package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;
import lombok.extern.slf4j.Slf4j;

/**
 * Fifth and final Commander concrete class in chain
 * Responsible for closing the game
 */
@Slf4j
public class FinishCommander implements GameCommander {

    // set the game status to finish that won't be accessible for play anymore
    @Override
    public void action(Game game, PlayDto playDto) {
        if (game.getChampion() != null && game.getChampion().getPlayer() != null) {
            game.setStatus(GameStatus.FINISHED);
            log.info("The Game Is Finished");
        }
    }
}
