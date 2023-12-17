package com.bol.mancala.game.rule.basic;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.rule.impl.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * invoker or aggregator class
 * which is based on command design pattern
 * weaving all responsible class in a sorted manner
 */
@Service
public class GameCommanderChain {
    private final List<GameCommander> executors;

    public GameCommanderChain() {
        executors = List.of(
                new InitialCommander(),
                new PitSelectionCommander(),
                new SeedSowCommander(),
                new ChampionCommander(),
                new FinishCommander()
        );
    }

    public void play(Game game, PlayDto playDto) throws CustomException {
        for (GameCommander commander : executors) {
            commander.action(game, playDto);
        }
    }
}
