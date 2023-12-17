package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.config.properties.ApplicationProperties;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.setup.GameSetupDto;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.game.GameSetting;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameSetupServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ApplicationProperties applicationProperties;

    @InjectMocks
    private GameSetupServiceImpl gameSetupService;

    @BeforeEach
    void setUp() {
        when(applicationProperties.getPlayerCountLimit()).thenReturn(2);
        when(applicationProperties.getPitCountLimit()).thenReturn(12);
        when(applicationProperties.getSeedCountPerPitLimit()).thenReturn(4);
        when(applicationProperties.getStoreCountLimit()).thenReturn(2);
        when(applicationProperties.getPitCountPerPlayerLimit()).thenReturn(6);
        when(applicationProperties.getStoreCountPerPlayerLimit()).thenReturn(1);

    }

    @Test
    void testCreateGameSetupOnValidInput() throws CustomException {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
        Assertions.assertEquals(game.getGameId(), gameSetupService.createGame(ModelMocker.createFullGameSetupDto()).getGameId());
    }

    @Test
    void testCreateGameSetupOnInvalidPlayerCount() throws CustomException {
        Game game = new Game();
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
        Assertions.assertThrows(CustomException.class, () -> gameSetupService.createGame(ModelMocker.createFullGameSetupDtoWithExtraPlayer()));
    }

    @Test
    void testCreateGameSetupOnNegativeGameSetting() throws CustomException {
        Game game = new Game();
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
        Assertions.assertThrows(CustomException.class, () -> gameSetupService.createGame(ModelMocker.createFullGameSetupDtoWithNegativeSetting()));
    }

    @Test
    void testCreateGameSetting() throws CustomException {
        Game game = new Game();
        GameSetting gameSettingMock = ModelMocker.createGameSetting();
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
        GameSetupDto gameSetupDto = ModelMocker.createFullGameSetupDto();
        GameSetting gameSettingByService = gameSetupService.createGameSetting(gameSetupDto.getGameSetting());
        Assertions.assertEquals(gameSettingMock.getTotalPits(), gameSettingByService.getTotalPits());
        Assertions.assertEquals(gameSettingMock.getTotalPitPerPlayer(), gameSettingByService.getTotalPitPerPlayer());
        Assertions.assertEquals(gameSettingMock.getTotalSeedPerPit(), gameSettingByService.getTotalSeedPerPit());
        Assertions.assertEquals(gameSettingMock.getTotalStores(), gameSettingByService.getTotalStores());
        Assertions.assertEquals(gameSettingMock.getPlayerCount(), gameSettingByService.getPlayerCount());
    }

    @Test
    void testInitializeGame() {
        Game game = new Game();
        GameSetting gameSetting = ModelMocker.createGameSetting();
        List<Player> players = ModelMocker.createPlayers();
        game.setGameSetting(gameSetting);
        Board board = ModelMocker.createBoard();
        game.setBoard(board);
        game.setPlayers(players);

        Game testableGame = new Game();
        testableGame.setGameSetting(gameSetting);
        testableGame.setPlayers(players);

        Game initializedGame = gameSetupService.initializeGame(testableGame);

        Assertions.assertEquals(initializedGame.getBoard().getPits().size(),game.getBoard().getPits().size());
        Assertions.assertEquals(initializedGame.getBoard().getPits().get(1).getSeedCount(),game.getBoard().getPits().get(1).getSeedCount());
        Assertions.assertEquals(initializedGame.getBoard().getPits().get(6).getSeedCount(),game.getBoard().getPits().get(6).getSeedCount());
        Assertions.assertEquals(initializedGame.getBoard().getPits().get(6).getSeedCount(),0);
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
    }


}