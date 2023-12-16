package com.bol.mancala.presentation.api;

import com.bol.mancala.presentation.constants.GameRestApi;
import com.bol.mancala.util.ModelMocker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.bol.mancala.util.TestUtil.asJsonString;
import static org.hamcrest.Matchers.anyOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GamePlayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGameApiWhenGameSettingIsNullAndPlayersIsNull() throws Exception {
        mockMvc.perform(post("/api" + GameRestApi.GAME_SETUP)
                        .contentType("application/json")
                        .content(asJsonString(ModelMocker.createGameSetupDtoWithoutGameSettingDtoAndPlayerListDto())))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("VALIDATION_FAILED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value(anyOf(
                        Matchers.is("must not be null"),
                        Matchers.is("must not be empty"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").value(anyOf(
                        Matchers.is("players"),
                        Matchers.is("gameSetting"))));
    }


}