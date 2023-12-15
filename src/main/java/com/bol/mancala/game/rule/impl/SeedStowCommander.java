package com.bol.mancala.game.rule.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.GameCommander;

import java.util.Optional;
import java.util.UUID;

public class SeedStowCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) throws CustomException {
        int lastIndex = this.stow(game, playDto.getPitIndex(), playDto.getPlayerId());
        this.checkLastStow(game, playDto.getPlayerId(), lastIndex);
    }

    private int stow(Game game, int pitIndex, UUID playerId) throws CustomException {
        Pit pit = game.getBoard().getPits().get(pitIndex);
        int seedCount = pit.getSeedCount();
        pit.setSeedCount(0);
        int currentIndex = pitIndex;

        for (int i = 0; i < seedCount; i++) {
            currentIndex = (currentIndex + 1) % (game.getGameSetting().getTotalPits() + game.getGameSetting().getTotalStores());
            Pit nextPit = game.getBoard().getPits().get(currentIndex);

            if (!nextPit.getPlayerId().equals(playerId) && nextPit.isStore()) {
                i--;
                continue;
            }
            nextPit.setSeedCount(nextPit.getSeedCount() + 1);
        }
        return currentIndex;
    }

    private void checkLastStow(Game game, UUID playerId, int lastIndex) {
        Pit pit = game.getBoard().getPits().get(lastIndex);
        if (pit.isStore() && pit.getPlayerId().equals(playerId)) {
            System.out.println("ur turn again");
        } else {
            handleLastStowOnOwnPit(game, pit, playerId);
        }
    }

    private void handleLastStowOnOwnPit(Game game, Pit pit, UUID playerId) {
        if (pit.getPlayerId().equals(playerId) && pit.getSeedCount() == 0) {
            int oppositePitIndex = getOppositePitIndex(game.getGameSetting().getTotalPitPerPlayer(), pit.getIndex());
            captureAllGainedSeeds(game, playerId, pit, game.getBoard().getPits().get(oppositePitIndex));
        }
    }

    private void changeTurn(Game game, UUID playerId) {
        Optional<Player> nextPlayer = game.getPlayers().stream().filter(player -> !player.getPlayerId().equals(playerId)).findFirst();
        nextPlayer.ifPresent(player -> game.getBoard().setActivePlayerId(player.getPlayerId()));
    }

    private void captureAllGainedSeeds(Game game, UUID playerId, Pit currentPit, Pit opponentPit) {
        Optional<Pit> probableStore = game.getBoard().getPits().stream().filter(pit -> pit.isStore() && pit.getPlayerId().equals(playerId)).findFirst();
        probableStore.ifPresent(store -> {
            if (currentPit.getSeedCount() > 0) {
                store.setSeedCount(store.getSeedCount() + opponentPit.getSeedCount() + 1);
                currentPit.setSeedCount(0);
                opponentPit.setSeedCount(0);
            } else {
                changeTurn(game, playerId);
            }
        });

    }

    private int getOppositePitIndex(int totalPitPerPlayer, int currentPitIndex) {
        return totalPitPerPlayer - currentPitIndex - 1;
    }
}
