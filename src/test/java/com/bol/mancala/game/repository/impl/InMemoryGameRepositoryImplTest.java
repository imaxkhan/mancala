package com.bol.mancala.game.repository.impl;

import com.bol.mancala.domain.model.game.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InMemoryGameRepositoryImplTest {
    @Autowired
    private InMemoryGameRepositoryImpl repository;

    @Test
    void testSaveOrUpdate() {
        Game entity = new Game();
        entity.setGameId(UUID.randomUUID());
        Game createdEntity = repository.saveOrUpdate(entity);
        assertEquals(entity, createdEntity);
    }

    @Test
    void testFindByGameIdWhenExists() {
        Game game = new Game();
        game.setGameId(UUID.randomUUID());
        Game savedEntity = repository.saveOrUpdate(game);
        Optional<Game> foundEntity = repository.findByGameId(savedEntity.getGameId());
        assertEquals(savedEntity, foundEntity.get());
    }

    @Test
    void testFindByGameIdWhenIdNotExists() {
        assertEquals(repository.findByGameId(UUID.randomUUID()), Optional.empty());
    }

}