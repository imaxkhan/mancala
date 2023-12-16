package com.bol.mancala.game.repository;

import com.bol.mancala.domain.model.game.Game;

import java.util.Optional;
import java.util.UUID;

/**
 * repository interface that can be in-memory or database level
 */
public interface GameRepository {
    /**
     *
     * @param game entity
     * @return game entity
     */
    Game saveOrUpdate(Game game);

    /**
     *
     * @param gameId of game
     * @return probable game in optional mode
     */
    Optional<Game> findByGameId(UUID gameId);
}
