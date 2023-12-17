package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChampionCommanderTest {
    private ChampionCommander championCommander;

    @BeforeEach
    void setUp() {
        championCommander = new ChampionCommander();
    }


    @Test
    void testCheckChampionWhenOneSideOfTheBoardIsEmpty() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board championBoard = ModelMocker.createChampionBoard();
        game.setBoard(championBoard);
        PlayDto playDto = new PlayDto();
        playDto.setPitIndex(1);
        playDto.setPlayerId(game.getPlayers().get(0).getPlayerId());
        championCommander.action(game, playDto);
        Assertions.assertNotNull(game.getChampion());
    }
    @Test
    void testCheckChampionWhenNoSideOfTheBoardIsEmpty() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board championBoard = ModelMocker.createBoard();
        game.setBoard(championBoard);
        PlayDto playDto = new PlayDto();
        playDto.setPitIndex(1);
        playDto.setPlayerId(game.getPlayers().get(0).getPlayerId());
        championCommander.action(game, playDto);
        Assertions.assertNull(game.getChampion());
    }
}