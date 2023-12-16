package com.bol.mancala.presentation.api;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.dto.play.PlayResultDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Api for player pit selection
 */
@RequestMapping("/api")
public interface GamePlayController {

    @PutMapping("/plays/{gameId}")
    ResponseEntity<PlayResultDto> play(@PathVariable("gameId") UUID gameId, @Valid @RequestBody PlayDto playDto) throws CustomException;

}
