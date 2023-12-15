package com.bol.mancala.game.service;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.dto.play.PlayResultDto;

import java.util.UUID;

public interface GamePlayService {
    PlayResultDto play(UUID gameId, PlayDto playDto) throws CustomException;
}
