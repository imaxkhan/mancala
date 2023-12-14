package com.bol.mancala.presentation.api;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerPickResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api")
public interface PlayerPickController {

    @PutMapping("/players/pick/{gameId}")
    ResponseEntity<PlayerPickResultDto> pickPlayer(@PathVariable("gameId") UUID gameId) throws CustomException;
}
