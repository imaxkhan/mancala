package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerRandomPickResultDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlayerRandomPickerServiceImplTest {

    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private PlayerRandomPickerServiceImpl playerRandomPickerService;

    @Test
    void testFindFirstPlayerTurnByValidGameId() throws CustomException {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        when(gameRepository.findByGameId(any())).thenReturn(Optional.of(game));
        PlayerRandomPickResultDto firstPlayerTurn = playerRandomPickerService.findFirstPlayerTurn(game.getGameId());
        Assertions.assertNotNull(firstPlayerTurn.getPlayer());
    }

    @Test
    void testFindFirstPlayerTurnByInvalidGameId() throws CustomException {
        when(gameRepository.findByGameId(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class,()->playerRandomPickerService.findFirstPlayerTurn(UUID.randomUUID()));
    }

    @Test
    void testFindFirstPlayerTurnWhenGameIsRunning() throws CustomException {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        game.setStatus(GameStatus.RUNNING);
        when(gameRepository.findByGameId(any())).thenReturn(Optional.of(game));
        Assertions.assertThrows(CustomException.class,()->playerRandomPickerService.findFirstPlayerTurn(game.getGameId()));
    }

    @Test
    void testUpdateGameSetting() throws CustomException {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        when(gameRepository.findByGameId(any())).thenReturn(Optional.of(game));
        playerRandomPickerService.updateGameSetup(game,game.getPlayers().get(0));
        Assertions.assertEquals(game.getBoard().getActivePlayer(),game.getPlayers().get(0));
    }


}