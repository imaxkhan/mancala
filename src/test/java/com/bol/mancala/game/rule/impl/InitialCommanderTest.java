package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InitialCommanderTest {

    private InitialCommander initialCommander;

    @BeforeEach
    void setUp() {
        initialCommander = new InitialCommander();
    }

    @Test
    void testPlayWhenGameStatusIsNotStarted() {
        Game game = new Game();
        game.setStatus(GameStatus.NOT_STARTED);
        Assertions.assertThrows(CustomException.class, () -> initialCommander.play(game, null));
    }

    @Test
    void testPlayWhenGameStatusIsFinished() {
        Game game = new Game();
        game.setStatus(GameStatus.FINISHED);
        Assertions.assertThrows(CustomException.class, () -> initialCommander.play(game, null));
    }


    @Test
    void testCheckTurnValidPlayer() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        board.setActivePlayer(game.getPlayers().get(0));
        game.setBoard(board);
        Assertions.assertDoesNotThrow(() -> initialCommander.checkTurn(board, game.getPlayers().get(0).getPlayerId()));
    }

    @Test
    void testCheckTurnInValidPlayer() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        board.setActivePlayer(game.getPlayers().get(0));
        game.setBoard(board);
        Assertions.assertThrows(CustomException.class, () -> initialCommander.checkTurn(board, game.getPlayers().get(1).getPlayerId()));
    }
}