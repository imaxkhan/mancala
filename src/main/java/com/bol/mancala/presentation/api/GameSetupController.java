package com.bol.mancala.presentation.api;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameDto;
import com.bol.mancala.domain.dto.Game.GameResultDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface GameSetupController {

    @PostMapping("/games/setup")
    ResponseEntity<GameResultDto> createGame(@Valid @RequestBody GameDto gameDto) throws CustomException;

}
