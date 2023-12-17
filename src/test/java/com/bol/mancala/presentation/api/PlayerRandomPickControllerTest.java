package com.bol.mancala.presentation.api;

import com.bol.mancala.domain.dto.Game.setup.GameSetupResultDto;
import com.bol.mancala.game.service.GameSetupService;
import com.bol.mancala.presentation.constants.GameRestApi;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerRandomPickControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameSetupService gameSetupService;

    @Test
    public void testPlayerRandomPickupApiWhenGameIdIsNotFound() throws Exception {
        mockMvc.perform(put("/api" + GameRestApi.PLAYER_RANDOM_PICK, "9c7da746-5280-43df-8831-fb641a1ee903")
                        .contentType("application/json")
                )
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("ENTITY_NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Data not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").doesNotExist());
    }

    @Test
    public void testPlayerRandomPickupApiWhenGameIdExists() throws Exception {
        GameSetupResultDto gameSetupResultDto = gameSetupService.createGame(ModelMocker.createFullGameSetupDto());
        mockMvc.perform(put("/api" + GameRestApi.PLAYER_RANDOM_PICK, gameSetupResultDto.getGameId())
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("RUNNING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player.playerId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.player.userName").exists());
    }

}