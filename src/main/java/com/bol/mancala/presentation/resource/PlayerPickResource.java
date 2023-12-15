package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerRandomPickResultDto;
import com.bol.mancala.game.service.impl.PlayerRandomPickerServiceImpl;
import com.bol.mancala.presentation.api.PlayerPickController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PlayerPickResource implements PlayerPickController {

    private final PlayerRandomPickerServiceImpl service;

    @Override
    public ResponseEntity<PlayerRandomPickResultDto> pickPlayer(UUID gameId) throws CustomException {
        PlayerRandomPickResultDto gameResultDto = service.findFirstPlayerTurn(gameId);
        return ResponseEntity.ok(gameResultDto);
    }
}
