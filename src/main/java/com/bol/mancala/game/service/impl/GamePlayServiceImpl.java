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

/**
 * An implementation of playing the game
 * Command Invoker service is wired to
 * handle all commander steps
 * to manage game rules and scenarios
 */
@Service
public class GamePlayServiceImpl implements GamePlayService {
    private final GameCommanderChain gameCommanderChain;
    private final GameRepository gameRepository;

    public GamePlayServiceImpl(GameCommanderChain gameCommanderChain, GameRepository gameRepository) {
        this.gameCommanderChain = gameCommanderChain;
        this.gameRepository = gameRepository;
    }

    /**
     * @param gameId
     * @param playDto
     * @return General Overview of the board after player sow on pits
     * @throws CustomException when game not found
     */
    @Override
    public PlayResultDto play(UUID gameId, PlayDto playDto) throws CustomException {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ENTITY_NOT_FOUND, "Data not found"));
        gameCommanderChain.play(game, playDto);
        return new PlayResultDto(game);
    }
}
