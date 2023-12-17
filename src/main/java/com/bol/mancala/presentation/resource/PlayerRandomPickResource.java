package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.dice.PlayerRandomPickResultDto;
import com.bol.mancala.game.service.impl.PlayerRandomPickerServiceImpl;
import com.bol.mancala.presentation.api.PlayerRandomPickController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PlayerRandomPickResource implements PlayerRandomPickController {

    private final PlayerRandomPickerServiceImpl service;

    @Override
    public ResponseEntity<PlayerRandomPickResultDto> pickPlayer(UUID gameId) throws CustomException {
        PlayerRandomPickResultDto gameResultDto = service.findFirstPlayerTurn(gameId);
        return ResponseEntity.ok(gameResultDto);
    }
}
