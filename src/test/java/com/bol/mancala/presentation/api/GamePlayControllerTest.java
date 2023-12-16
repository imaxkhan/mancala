package com.bol.mancala.presentation.api;

import com.bol.mancala.domain.dto.Game.GameSetupResultDto;
import com.bol.mancala.domain.dto.Game.PlayerResultDto;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.service.GameSetupService;
import com.bol.mancala.presentation.constants.GameRestApi;
import com.bol.mancala.util.ModelMocker;
import com.bol.mancala.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GamePlayControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameSetupService gameSetupService;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testGamePlayApiWhenGameIdIsNotFound() throws Exception {
        gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, "9c7da746-5280-43df-8831-fb641a1ee904")
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(ModelMocker.createFullPlayDto()))
                )
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("ENTITY_NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Data not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testGamePlayApiWhenPlayerIdIsNull() throws Exception {
        gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, "9c7da746-5280-43df-8831-fb641a1ee904")
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(ModelMocker.createPlayDtoWithoutPlayerId()))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("must not be null"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value("playerId"));
    }

    @Test
    public void testGamePlayApiWhenGameIsNotStarted() throws Exception {
        GameSetupResultDto resultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        Optional<Game> probableGame = gameRepository.findByGameId(resultDto.getGameId());
        probableGame.ifPresent(game -> game.setStatus(GameStatus.NOT_STARTED));

        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, resultDto.getGameId())
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(ModelMocker.createFullPlayDto()))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("game setup not fully done"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testGamePlayApiWhenGameIsFinished() throws Exception {
        GameSetupResultDto resultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        Optional<Game> probableGame = gameRepository.findByGameId(resultDto.getGameId());
        PlayerResultDto playerResultDtoFirst = resultDto.getPlayers().get(0);
        PlayerResultDto playerResultDtoSecond = resultDto.getPlayers().get(1);
        probableGame.ifPresent(game -> {
            game.setStatus(GameStatus.RUNNING);
            Player player = Player.builder()
                    .userName(playerResultDtoFirst.getUserName())
                    .playerId(playerResultDtoFirst.getPlayerId())
                    .build();
            game.getBoard().setActivePlayer(player);
        });
        PlayDto fullPlayDto = ModelMocker.createFullPlayDto();
        fullPlayDto.setPlayerId(playerResultDtoSecond.getPlayerId());

        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, resultDto.getGameId())
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(fullPlayDto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("its not your turn"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testGamePlayApiWhenPitOwnerIsWrong() throws Exception {
        GameSetupResultDto resultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        Optional<Game> probableGame = gameRepository.findByGameId(resultDto.getGameId());
        PlayerResultDto playerResultDtoFirst = resultDto.getPlayers().get(0);
        PlayerResultDto playerResultDtoSecond = resultDto.getPlayers().get(1);
        PlayDto fullPlayDto = ModelMocker.createFullPlayDto();
        fullPlayDto.setPlayerId(playerResultDtoFirst.getPlayerId());
        probableGame.ifPresent(game -> {
            game.setStatus(GameStatus.RUNNING);
            Player player = Player.builder()
                    .userName(playerResultDtoFirst.getUserName())
                    .playerId(playerResultDtoFirst.getPlayerId())
                    .build();
            game.getBoard().setActivePlayer(player);
            Optional<Pit> firstPitOfSecondPlayer = game.getBoard().getPits().stream().filter(pit -> pit.getPlayer().getPlayerId().equals(playerResultDtoSecond.getPlayerId())).findFirst();
            firstPitOfSecondPlayer.ifPresent(pit -> {
                fullPlayDto.setPitIndex(pit.getIndex());
            });
        });


        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, resultDto.getGameId())
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(fullPlayDto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Pit does not belong to you"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testGamePlayApiWhenPitCapacityIsZeroNotStore() throws Exception {
        GameSetupResultDto resultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        Optional<Game> probableGame = gameRepository.findByGameId(resultDto.getGameId());
        PlayerResultDto playerResultDtoFirst = resultDto.getPlayers().get(0);
        PlayDto fullPlayDto = ModelMocker.createFullPlayDto();
        fullPlayDto.setPlayerId(playerResultDtoFirst.getPlayerId());
        probableGame.ifPresent(game -> {
            game.setStatus(GameStatus.RUNNING);
            Player player = Player.builder()
                    .userName(playerResultDtoFirst.getUserName())
                    .playerId(playerResultDtoFirst.getPlayerId())
                    .build();
            game.getBoard().setActivePlayer(player);
            Optional<Pit> firstPitOfFirstPlayer = game.getBoard().getPits().stream().filter(pit -> pit.getPlayer().getPlayerId().equals(playerResultDtoFirst.getPlayerId()) && !pit.isStore()).findFirst();
            firstPitOfFirstPlayer.ifPresent(pit -> {
                pit.setSeedCount(0);
                fullPlayDto.setPitIndex(pit.getIndex());
            });
        });

        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, resultDto.getGameId())
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(fullPlayDto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Pit is empty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testGamePlayApiWhenPitIsStore() throws Exception {
        GameSetupResultDto resultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        Optional<Game> probableGame = gameRepository.findByGameId(resultDto.getGameId());
        PlayerResultDto playerResultDtoFirst = resultDto.getPlayers().get(0);
        PlayDto fullPlayDto = ModelMocker.createFullPlayDto();
        fullPlayDto.setPlayerId(playerResultDtoFirst.getPlayerId());
        probableGame.ifPresent(game -> {
            game.setStatus(GameStatus.RUNNING);
            Player player = Player.builder()
                    .userName(playerResultDtoFirst.getUserName())
                    .playerId(playerResultDtoFirst.getPlayerId())
                    .build();
            game.getBoard().setActivePlayer(player);
            Optional<Pit> firstPitOfFirstPlayer = game.getBoard().getPits().stream().filter(pit -> pit.getPlayer().getPlayerId().equals(playerResultDtoFirst.getPlayerId()) && pit.isStore()).findFirst();
            firstPitOfFirstPlayer.ifPresent(pit -> {
                fullPlayDto.setPitIndex(pit.getIndex());
            });
        });

        mockMvc.perform(put("/api" + GameRestApi.GAME_PLAY, resultDto.getGameId())
                        .contentType("application/json")
                        .content(TestUtil.asJsonString(fullPlayDto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("First Move must be pit not store"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

}