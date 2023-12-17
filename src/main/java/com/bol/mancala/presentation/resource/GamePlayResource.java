package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.action.PlayDto;
import com.bol.mancala.domain.dto.play.action.PlayResultDto;
import com.bol.mancala.game.service.GamePlayService;
import com.bol.mancala.presentation.api.GamePlayController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class GamePlayResource implements GamePlayController {

    private final GamePlayService service;

    @Override
    public ResponseEntity<PlayResultDto> play(UUID gameId, PlayDto playDto) throws CustomException {
        PlayResultDto result = service.play(gameId, playDto);
        return ResponseEntity.ok(result);
    }
}
