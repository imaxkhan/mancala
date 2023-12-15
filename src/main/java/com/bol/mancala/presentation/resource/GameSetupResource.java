package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameDto;
import com.bol.mancala.domain.dto.Game.GameResultDto;
import com.bol.mancala.game.service.impl.GameSetupServiceImpl;
import com.bol.mancala.presentation.api.GameSetupController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class GameSetupResource implements GameSetupController {

    private final GameSetupServiceImpl service;

    @Override
    public ResponseEntity<GameResultDto> createGame(@Valid GameDto gameDto) throws CustomException {
        GameResultDto gameResultDto = service.createGame(gameDto);
        return ResponseEntity.ok(gameResultDto);
    }
}
