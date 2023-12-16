package com.bol.mancala.presentation.api;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameSetupDto;
import com.bol.mancala.domain.dto.Game.GameSetupResultDto;
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

    @PostMapping("/games/setup")
    ResponseEntity<GameSetupResultDto> createGame(@Valid @RequestBody GameSetupDto gameDto) throws CustomException;

}
