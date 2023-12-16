package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.container.LastPitContainerInfo;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeedSowCommanderTest {

    private SeedSowCommander seedSowCommander;

    @BeforeEach
    void setUp() {
        seedSowCommander = new SeedSowCommander();
    }

    @Test
    void testSowLastIndexValid() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        game.getBoard().setActivePlayer(game.getPlayers().get(0));
        Assertions.assertEquals(4, seedSowCommander.sow(game, 0, game.getPlayers().get(0).getPlayerId()).getCurrentIndex());
    }

    @Test
    void testSowLastIndexOnOwnStoreValid() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        Player player = game.getBoard().getPits().get(0).getPlayer();
        game.getBoard().setActivePlayer(player);
        LastPitContainerInfo lastPitContainerInfo = seedSowCommander.sow(game, 2, player.getPlayerId());
        Assertions.assertEquals(6, lastPitContainerInfo.getCurrentIndex());
        Assertions.assertEquals(game.getBoard().getPits().get(6).getIndex(), lastPitContainerInfo.getCurrentIndex());
        Assertions.assertTrue(game.getBoard().getPits().get(6).isStore());
        Assertions.assertEquals(game.getBoard().getPits().get(6).getPlayer().getPlayerId(), player.getPlayerId());
    }

    @Test
    void testPlayOnOwnNonEmptyPitNoChangeInTurn() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        Player player = game.getBoard().getPits().get(0).getPlayer();
        game.getBoard().setActivePlayer(player);
        game.getBoard().getPits().get(0).setSeedCount(1);
        game.getBoard().getPits().get(5).setSeedCount(8);

        PlayDto playDto = new PlayDto();
        playDto.setPitIndex(5);
        playDto.setPlayerId(player.getPlayerId());
        seedSowCommander.play(game, playDto);
        Assertions.assertEquals(game.getBoard().getPits().get(0).getPlayer().getPlayerId(), player.getPlayerId());
        Assertions.assertNotEquals(game.getBoard().getActivePlayer().getPlayerId(), player.getPlayerId());
    }

    @Test
    void testPlayOnOwnStoreNoChangeInTurn() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        Player player = game.getBoard().getPits().get(0).getPlayer();
        game.getBoard().setActivePlayer(player);
        game.getBoard().getPits().get(2).setSeedCount(4);

        PlayDto playDto = new PlayDto();
        playDto.setPitIndex(2);
        playDto.setPlayerId(player.getPlayerId());
        seedSowCommander.play(game, playDto);
        Assertions.assertTrue(game.getBoard().getPits().get(6).isStore());
        Assertions.assertEquals(game.getBoard().getPits().get(6).getPlayer().getPlayerId(), player.getPlayerId());
        Assertions.assertEquals(game.getBoard().getActivePlayer().getPlayerId(), player.getPlayerId());
        Assertions.assertEquals(1,game.getBoard().getPits().get(6).getSeedCount());
    }

    @Test
    void testPlayCaptureWhenLastIndexIsOnOwnEmptyPit() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        Player player = game.getBoard().getPits().get(0).getPlayer();
        game.getBoard().setActivePlayer(player);
        game.getBoard().getPits().get(0).setSeedCount(0);
        game.getBoard().getPits().get(5).setSeedCount(8);

        PlayDto playDto = new PlayDto();
        playDto.setPitIndex(5);
        playDto.setPlayerId(player.getPlayerId());
        seedSowCommander.play(game, playDto);
        Assertions.assertEquals(game.getBoard().getPits().get(0).getPlayer().getPlayerId(), player.getPlayerId());
        Assertions.assertEquals(0,game.getBoard().getPits().get(0).getSeedCount());
        Assertions.assertEquals(0,game.getBoard().getPits().get(12).getSeedCount());
        Assertions.assertEquals(7,game.getBoard().getPits().get(6).getSeedCount());
    }

}