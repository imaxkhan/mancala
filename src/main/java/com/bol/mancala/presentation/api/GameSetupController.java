package com.bol.mancala.presentation.api;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.setup.GameSetupDto;
import com.bol.mancala.domain.dto.Game.setup.GameSetupResultDto;
import com.bol.mancala.presentation.constants.GameRestApi;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Api for creating gameSetup with setting and players
 */
@RequestMapping("/api")
public interface GameSetupController {

    @PostMapping(GameRestApi.GAME_SETUP)
    ResponseEntity<GameSetupResultDto> createGame(@Valid @RequestBody GameSetupDto gameDto) throws CustomException;

}
