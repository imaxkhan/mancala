package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinishCommanderTest {

    private FinishCommander finishCommander;

    @BeforeEach
    void setUp() {
        finishCommander = new FinishCommander();
    }

    @Test
    void testPlayWhenGameIsFinished() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createChampionBoard());
        game.setChampion(new Champion(game.getPlayers().get(0), 30));
        PlayDto playDto = new PlayDto();
        playDto.setPlayerId(game.getPlayers().get(0).getPlayerId());
        playDto.setPitIndex(1);
        finishCommander.action(game, playDto);
        Assertions.assertEquals(game.getStatus(), GameStatus.FINISHED);
    }

    @Test
    void testPlayWhenGameIsNotFinished() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setStatus(GameStatus.RUNNING);
        game.setBoard(ModelMocker.createBoard());
        PlayDto playDto = new PlayDto();
        playDto.setPlayerId(game.getPlayers().get(0).getPlayerId());
        playDto.setPitIndex(1);
        finishCommander.action(game, playDto);
        Assertions.assertEquals(game.getStatus(), GameStatus.RUNNING);
    }
}