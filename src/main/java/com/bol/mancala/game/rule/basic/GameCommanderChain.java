package com.bol.mancala.game.rule.basic;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.impl.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameCommanderChain {
    private final List<GameCommander> executors;

    public GameCommanderChain() {
        executors = List.of(
                new InitialCommander(),
                new PitSelectionCommander(),
                new SeedStowCommander(),
                new ChampionCommander(),
                new FinishCommander()
        );
    }

    public void play(Game game, PlayDto playDto) throws CustomException {
        for (GameCommander commander : executors) {
            commander.play(game, playDto);
        }
    }
}
