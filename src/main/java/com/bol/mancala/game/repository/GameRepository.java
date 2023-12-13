package com.bol.mancala.game.repository;

import com.bol.mancala.domain.model.game.Game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    Game saveOrUpdate(Game game);

    Optional<Game> findByGameId(UUID gameId);
}
