package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayerPickResultDto;
import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.service.PlayerPicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PlayerPickService implements PlayerPicker {

    private final GameRepository gameRepository;

    @Autowired
    public PlayerPickService(@Qualifier("GM-IM-RP") GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public PlayerPickResultDto findFirstPlayerTurn(UUID gameId) throws CustomException {
        Game game = checkGameStatus(gameId);
        Player firstPlayer = checkPlayer(game);
        updateGameSetup(game, firstPlayer);
        return new PlayerPickResultDto(gameId, firstPlayer);
    }

    @Override
    public Game checkGameStatus(UUID gameId) throws CustomException {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ENTITY_NOT_FOUND, "Data not found"));

        if (game.getStatus().equals(GameStatus.RUNNING)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "First Player Already Selected");
        }
        return game;
    }

    @Override
    public Player checkPlayer(Game game) throws CustomException {
        List<Player> players = game.getPlayers();

        return players.stream()
                .skip(ThreadLocalRandom.current().nextInt(players.size()))
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomErrorCode.VALIDATION_FAILED, "Player Size Mismatch, Please recreate the game"));
    }

    @Override
    public void updateGameSetup(Game game, Player firstPlayer) {
        firstPlayer.getPlayerBoard().setMyTurn(true);
        game.setStatus(GameStatus.RUNNING);
        gameRepository.saveOrUpdate(game);
    }
}
