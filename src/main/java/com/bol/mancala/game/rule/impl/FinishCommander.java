package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.basic.GameCommander;

public class FinishCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) {
        if (game.getChampion() != null && game.getChampion().getPlayer() != null) {
            game.setStatus(GameStatus.FINISHED);
        }
    }
}
