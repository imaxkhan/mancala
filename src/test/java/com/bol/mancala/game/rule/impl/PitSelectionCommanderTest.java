package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.util.ModelMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class PitSelectionCommanderTest {

    private PitSelectionCommander pitSelectionCommander;

    @BeforeEach
    void setUp() {
        pitSelectionCommander = new PitSelectionCommander();
    }

    @Test
    void testCheckOwnerWhenIsNotValid() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createBoard());
        Assertions.assertThrows(CustomException.class, () -> pitSelectionCommander.checkOwner(game, UUID.randomUUID(), 0));
    }

    @Test
    void testCheckOwnerWhenIsValid() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createBoard());
        Pit pit = game.getBoard().getPits().get(0);
        Assertions.assertDoesNotThrow(() -> pitSelectionCommander.checkOwner(game, pit.getPlayer().getPlayerId(), pit.getIndex()));
    }

    @Test
    void testCheckCapacityZero() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createBoard());
        Pit pit = game.getBoard().getPits().get(0);
        pit.setSeedCount(0);
        Assertions.assertThrows(CustomException.class, () -> pitSelectionCommander.checkCapacity(game, pit.getIndex()));
    }

    @Test
    void testCheckCapacityMoreThanZero() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createBoard());
        Pit pit = game.getBoard().getPits().get(0);
        pit.setSeedCount(1);
        Assertions.assertDoesNotThrow(() -> pitSelectionCommander.checkCapacity(game, pit.getIndex()));
    }

    @Test
    void testCheckStoreOnFirstMove() {
        Game game = new Game(ModelMocker.createGameSetting(), ModelMocker.createPlayers());
        game.setBoard(ModelMocker.createBoard());
        Optional<Pit> probableStore = game.getBoard().getPits().stream().filter(Pit::isStore).findFirst();
        Assertions.assertThrows(CustomException.class, () -> pitSelectionCommander.checkCapacity(game, probableStore.get().getIndex()));
    }
}