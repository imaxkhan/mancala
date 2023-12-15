package com.bol.mancala.game.rule.basic;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.game.Game;

public interface GameCommander {
    void play(Game game,PlayDto playDto) throws CustomException;
}
