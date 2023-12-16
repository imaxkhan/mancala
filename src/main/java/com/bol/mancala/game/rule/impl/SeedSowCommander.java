package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.GameCommander;

import java.util.Optional;
import java.util.UUID;

/**
 * Third Commander concrete class in chain
 * main method for implementing game rules
 */
public class SeedSowCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) throws CustomException {
        int lastIndex = this.stow(game, playDto.getPitIndex(), playDto.getPlayerId());
        this.checkLastStow(game, playDto.getPlayerId(), lastIndex);
    }

    /**
     * sow the seeds in counter-wise manner
     * sow one by one in each pit and store(big pit but not for opponent)
     *
     * @param game     entity
     * @param pitIndex unique identifier of pit
     * @param playerId of the game in his/her turn
     * @return lastIndex of pit when all seeds are sowed
     * @throws CustomException when inner methods decide
     */
    private int stow(Game game, int pitIndex, UUID playerId) throws CustomException {
        Pit pit = game.getBoard().getPits().get(pitIndex);
        int seedCount = pit.getSeedCount();
        pit.setSeedCount(0);
        int currentIndex = pitIndex;

        for (int i = 0; i < seedCount; i++) {
            currentIndex = (currentIndex + 1) % (game.getGameSetting().getTotalPits() + game.getGameSetting().getTotalStores());
            Pit nextPit = game.getBoard().getPits().get(currentIndex);

            if (!nextPit.getPlayer().getPlayerId().equals(playerId) && nextPit.isStore()) {
                i--;
                continue;
            }
            nextPit.setSeedCount(nextPit.getSeedCount() + 1);
        }
        return currentIndex;
    }

    /**
     * check if last pit is a store, then player turn won't change
     * otherwise other method decide
     *
     * @param game
     * @param playerId
     * @param lastIndex last pit index after sowing the seeds
     */
    private void checkLastStow(Game game, UUID playerId, int lastIndex) {
        Pit pit = game.getBoard().getPits().get(lastIndex);
        if (pit.isStore() && pit.getPlayer().getPlayerId().equals(playerId)) {
            System.out.println("ur turn again");
        } else {
            handleLastSowOnOwnPit(game, pit, playerId);
        }
    }

    /**
     * if last pit its own pit and is empty
     * gather opposite index of opponent seeds and stow them in own store
     * if last pit is not empty on own pit then change player turn
     *
     * @param game
     * @param pit
     * @param playerId
     */
    private void handleLastSowOnOwnPit(Game game, Pit pit, UUID playerId) {
        if (pit.getPlayer().getPlayerId().equals(playerId) && pit.getSeedCount() == 0) {
            int oppositePitIndex = getOppositePitIndex(game.getGameSetting().getTotalPits(), pit.getIndex());
            captureAllGainedSeeds(game, playerId, pit, game.getBoard().getPits().get(oppositePitIndex));
        } else {
            changeTurn(game, playerId);
        }
    }

    /**
     * to change player turn on game board
     *
     * @param game
     * @param playerId
     */
    private void changeTurn(Game game, UUID playerId) {
        Optional<Player> nextPlayer = game.getPlayers().stream().filter(player -> !player.getPlayerId().equals(playerId)).findFirst();
        nextPlayer.ifPresent(player -> game.getBoard().setActivePlayer(player));
    }

    /**
     * to capture all the seeds on opponent pit and own pit to store
     * based on rule of former method
     *
     * @param game
     * @param playerId
     * @param currentPit
     * @param opponentPit
     */
    private void captureAllGainedSeeds(Game game, UUID playerId, Pit currentPit, Pit opponentPit) {
        Optional<Pit> probableStore = game.getBoard().getPits()
                .stream()
                .filter(pit -> pit.isStore() && pit.getPlayer().getPlayerId().equals(playerId))
                .findFirst();
        probableStore.ifPresent(store -> {
            if (currentPit.getSeedCount() == 0) {
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
    private int getOppositePitIndex(int totalPits, int currentPitIndex) {
        return totalPits - currentPitIndex;
    }
}
