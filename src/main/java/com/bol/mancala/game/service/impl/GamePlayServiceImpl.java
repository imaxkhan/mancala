package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.dto.play.PlayResultDto;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.rule.basic.GameCommanderChain;
import com.bol.mancala.game.service.GamePlayService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GamePlayServiceImpl implements GamePlayService {
    private final GameCommanderChain gameCommanderChain;
    private final GameRepository gameRepository;

    public GamePlayServiceImpl(GameCommanderChain gameCommanderChain, GameRepository gameRepository) {
        this.gameCommanderChain = gameCommanderChain;
        this.gameRepository = gameRepository;
    }

    @Override
    public PlayResultDto play(UUID gameId, PlayDto playDto) throws CustomException {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.VALIDATION_FAILED, "Game not found"));
        gameCommanderChain.play(game, playDto);
        return new PlayResultDto(game);
    }
}
