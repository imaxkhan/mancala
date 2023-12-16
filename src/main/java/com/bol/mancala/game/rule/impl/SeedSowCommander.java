package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.GameCommander;
import com.bol.mancala.game.rule.basic.container.LastPitContainerInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

/**
 * Third Commander concrete class in chain
 * main method for implementing game rules
 */
@Slf4j
public class SeedSowCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) {
        LastPitContainerInfo lastSowInfo = this.sow(game, playDto.getPitIndex(), playDto.getPlayerId());
        this.checkLastStow(game, playDto.getPlayerId(), lastSowInfo);
        log.info("After Sowing The Seeds, Last Pit Index Is " + lastSowInfo.getCurrentIndex());
    }

    /**
     * sow the seeds in counter-wise manner
     * sow one by one in each pit and store(big pit but not for opponent)
     *
     * @param game     entity
     * @param pitIndex unique identifier of pit
     * @param playerId of the game in his/her turn
     * @return lastIndex of pit when all seeds are sowed
     */
    public LastPitContainerInfo sow(Game game, int pitIndex, UUID playerId) {
        Pit pit = game.getBoard().getPits().get(pitIndex);
        int seedCount = pit.getSeedCount();
        pit.setSeedCount(0);
        int currentIndex = pitIndex;

        int lastIndexOldSeedCount = 0;
        for (int i = 0; i < seedCount; i++) {
            currentIndex = (currentIndex + 1) % (game.getGameSetting().getTotalPits() + game.getGameSetting().getTotalStores());
            Pit nextPit = game.getBoard().getPits().get(currentIndex);

            if (!nextPit.getPlayer().getPlayerId().equals(playerId) && nextPit.isStore()) {
                i--;
                continue;
            }
            lastIndexOldSeedCount = nextPit.getSeedCount();
            nextPit.setSeedCount(nextPit.getSeedCount() + 1);
        }
        return new LastPitContainerInfo(currentIndex, lastIndexOldSeedCount);
    }

    /**
     * check if last pit is a store, then player turn won't change
     * otherwise other method decide
     *
     * @param lastIndexInfo last pit index and old capacity of last pit
     */
    private void checkLastStow(Game game, UUID playerId, LastPitContainerInfo lastIndexInfo) {
        Pit pit = game.getBoard().getPits().get(lastIndexInfo.getCurrentIndex());
        if (pit.isStore() && pit.getPlayer().getPlayerId().equals(playerId)) {
            log.info("Last Index Is Store(BigPit), So It's Your Turn Again");
        } else {
            handleLastSowOnOwnPit(game, pit, lastIndexInfo.getOldSeedCount(), playerId);
        }
    }

    /**
     * * if last pit its own pit and is empty
     * * gather opposite index of opponent seeds and stow them in own store
     * * if last pit is not empty on own pit then change player turn
     *
     * @param currentPit  means last index
     * @param oldCapacity before updating
     */
    private void handleLastSowOnOwnPit(Game game, Pit currentPit, int oldCapacity, UUID playerId) {
        if (currentPit.getPlayer().getPlayerId().equals(playerId) && oldCapacity == 0) {
            int oppositePitIndex = getOppositePitIndex(game.getGameSetting().getTotalPits(), currentPit.getIndex());
            captureAllGainedSeeds(game, playerId, oldCapacity, currentPit, game.getBoard().getPits().get(oppositePitIndex));
        } else {
            changeTurn(game, playerId);
        }
    }

    /**
     * to change player turn on game board
     */
    private void changeTurn(Game game, UUID playerId) {
        Optional<Player> nextPlayer = game.getPlayers().stream().filter(player -> !player.getPlayerId().equals(playerId)).findFirst();
        nextPlayer.ifPresent(player -> game.getBoard().setActivePlayer(player));
    }

    /**
     * to capture all the seeds on opponent pit and own pit to store
     * based on rule of former method
     *
     * @param currentPit  means last index
     * @param opponentPit counter wise index
     */
    private void captureAllGainedSeeds(Game game, UUID playerId, int currentPitOldCapacity, Pit currentPit, Pit opponentPit) {
        Optional<Pit> probableStore = game.getBoard().getPits()
                .stream()
                .filter(pit -> pit.isStore() && pit.getPlayer().getPlayerId().equals(playerId))
                .findFirst();
        probableStore.ifPresent(store -> {
            if (currentPitOldCapacity == 0) {
                store.setSeedCount(store.getSeedCount() + opponentPit.getSeedCount() + 1);
                currentPit.setSeedCount(0);
                opponentPit.setSeedCount(0);
            } else {
                changeTurn(game, playerId);
            }
        });
    }

    /**
     * @param totalPits       of the game
     * @param currentPitIndex last index of pit after complete sow
     * @return the counter side of own pit in opponent arena
     */
    public static int getOppositePitIndex(int totalPits, int currentPitIndex) {
        return totalPits - currentPitIndex;
    }
}
