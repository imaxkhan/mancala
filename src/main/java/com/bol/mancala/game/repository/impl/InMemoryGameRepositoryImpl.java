package com.bol.mancala.game.repository.impl;

import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.game.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * in-memory implementation of game repository
 */
@Repository("GM-IM-RP")
public class InMemoryGameRepositoryImpl implements GameRepository {

    private final Map<UUID, Game> gameBucket = new ConcurrentHashMap<>();

    @Override
    public Game saveOrUpdate(Game game) {
        gameBucket.put(game.getGameId(), game);
        return game;
    }

    @Override
    public Optional<Game> findByGameId(UUID gameId) {
        return Optional.ofNullable(gameBucket.get(gameId));
    }
}
