package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.Game.GameDto;
import com.bol.mancala.domain.dto.Game.GameResultDto;
import com.bol.mancala.game.service.impl.GameSetupService;
import com.bol.mancala.presentation.api.GameSetupController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameSetupResource implements GameSetupController {

    private final GameSetupService service;

    @Override
    public ResponseEntity<GameResultDto> createGame(GameDto gameDto) throws CustomException {
        GameResultDto gameResultDto = service.createGame(gameDto);
        return ResponseEntity.ok(gameResultDto);
    }
}
