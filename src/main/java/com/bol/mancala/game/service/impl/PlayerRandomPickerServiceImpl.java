package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerRandomPickResultDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.service.PlayerRandomPickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class for managing user random selection
 * like dice in game to find the first player
 */
@Service
public class PlayerRandomPickerServiceImpl implements PlayerRandomPickerService {

    private final GameRepository gameRepository;

    @Autowired
    public PlayerRandomPickerServiceImpl(@Qualifier("GM-IM-RP") GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * find the first player and update game board active player field
     * @param gameId
     * @return first player information
     * @throws CustomException by inner methods
     */
    @Override
    public PlayerRandomPickResultDto findFirstPlayerTurn(UUID gameId) throws CustomException {
        Game game = checkGameStatus(gameId);
        Player firstPlayer = checkPlayer(game);
        updateGameSetup(game, firstPlayer);
        return new PlayerRandomPickResultDto(gameId, firstPlayer);
    }

    /**
     *
     * @param gameId
     * @return game entity
     * @throws CustomException when game not found or first player already selected
     */
    @Override
    public Game checkGameStatus(UUID gameId) throws CustomException {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ENTITY_NOT_FOUND, "Data not found"));

        if (game.getStatus().equals(GameStatus.RUNNING)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "First Player Already Selected");
        }
        return game;
    }

    /**
     *
     * @param game
     * @return random player based on size of players registered
     * with thread local random method
     * @throws CustomException
     */
    @Override
    public Player checkPlayer(Game game) throws CustomException {
        List<Player> players = game.getPlayers();

        return players.stream()
                .skip(ThreadLocalRandom.current().nextInt(players.size()))
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomErrorCode.VALIDATION_FAILED, "Player Size Mismatch, Please recreate the game"));
    }

    // to update game status to running after finding the first player
    @Override
    public void updateGameSetup(Game game, Player firstPlayer) {
        game.getBoard().setActivePlayer(firstPlayer);
        game.setStatus(GameStatus.RUNNING);
        gameRepository.saveOrUpdate(game);
    }
}
