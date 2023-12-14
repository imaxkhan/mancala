package com.bol.mancala.presentation.resource;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerPickResultDto;
import com.bol.mancala.game.service.impl.PlayerPickService;
import com.bol.mancala.presentation.api.PlayerPickController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PlayerPickResource implements PlayerPickController {

    private final PlayerPickService service;

    @Override
    public ResponseEntity<PlayerPickResultDto> pickPlayer(UUID gameId) throws CustomException {
        PlayerPickResultDto gameResultDto = service.findFirstPlayerTurn(gameId);
        return ResponseEntity.ok(gameResultDto);
    }
}
