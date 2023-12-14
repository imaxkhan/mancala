package com.bol.mancala.presentation.api;

import com.bol.mancala.domain.dto.Game.GameDto;
import com.bol.mancala.domain.dto.Game.GameSettingDto;
import com.bol.mancala.domain.dto.Game.PlayerDto;
import com.bol.mancala.domain.model.game.GameSetting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameSetupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGameApiWhenGameSettingIsNullAndPlayersIsNull() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setGameSetting(null);
        gameDto.setPlayers(null);
        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value(Matchers.containsInAnyOrder("must not be null", "must not be empty")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value(Matchers.containsInAnyOrder("players", "gameSetting")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenGameSettingIsNull() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setGameSetting(null);
        PlayerDto player1=new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2=new PlayerDto();
        player2.setUserName("rozhin");
        gameDto.setPlayers(Arrays.asList(player1,player2));

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("must not be null"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value( "gameSetting"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenPlayerIsNull() throws Exception {
        GameDto gameDto = new GameDto();
        GameSettingDto gameSetting=new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameDto.setGameSetting(gameSetting);
        gameDto.setPlayers(null);

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("must not be empty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value( "players"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenPlayerUserNameIsNull() throws Exception {
        GameDto gameDto = new GameDto();
        GameSettingDto gameSetting=new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameDto.setGameSetting(gameSetting);
        PlayerDto player1=new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2=new PlayerDto();
        player2.setUserName(null);
        gameDto.setPlayers(Arrays.asList(player1,player2));

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("username is empty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenPlayerUserNameIsEmpty() throws Exception {
        GameDto gameDto = new GameDto();
        GameSettingDto gameSetting=new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameDto.setGameSetting(gameSetting);
        PlayerDto player1=new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2=new PlayerDto();
        player2.setUserName("");
        gameDto.setPlayers(Arrays.asList(player1,player2));

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("username is empty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenGameSettingFieldsAreNegative() throws Exception {
        GameDto gameDto = new GameDto();
        GameSettingDto gameSetting=new GameSettingDto();
        gameSetting.setPlayerCount(-1);
        gameSetting.setTotalPits(0);
        gameSetting.setTotalSeedPerPit(-2);
        gameSetting.setTotalStores(-6);
        gameDto.setGameSetting(gameSetting);
        PlayerDto player1=new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2=new PlayerDto();
        player2.setUserName("reza");
        gameDto.setPlayers(Arrays.asList(player1,player2));

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value(Matchers.containsString("must be")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value(Matchers.containsInAnyOrder("Player Size","pit count","store count")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trace").doesNotExist());
    }

    @Test
    public void testGameApiWhenGameSettingAndPlayerAreValid() throws Exception {
        GameDto gameDto = new GameDto();
        GameSettingDto gameSetting=new GameSettingDto();
        gameSetting.setPlayerCount(2);
        gameSetting.setTotalPits(12);
        gameSetting.setTotalSeedPerPit(4);
        gameSetting.setTotalStores(2);
        gameDto.setGameSetting(gameSetting);
        PlayerDto player1=new PlayerDto();
        player1.setUserName("imax");
        PlayerDto player2=new PlayerDto();
        player2.setUserName("reza");
        gameDto.setPlayers(Arrays.asList(player1,player2));

        var result = mockMvc.perform(post("/api/games")
                        .contentType("application/json")
                        .content(asJsonString(gameDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameId").isNotEmpty());
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}