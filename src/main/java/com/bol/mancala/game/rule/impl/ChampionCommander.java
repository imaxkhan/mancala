package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.GameCommander;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Forth Commander concrete class in chain
 * Responsible for finding the champion after each round of sow by player
 */
@Slf4j
public class ChampionCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) {
        checkChampion(game);
    }

    /**
     * finding empty pits in each side of the board except store
     * first player who makes his pits empty will trigger ending of the game
     * but this does not mean to be winner
     * so pits will be group by player and non-store pits
     * the player with most seeds including seeds in stores is winner
     *
     * @param game
     */
    private void checkChampion(Game game) {
        Map<Player, Integer> seedCountPerPitGroupByPlayer = game.getBoard().getPits()
                .stream()
                .filter(pit -> !pit.isStore())
                .collect(groupingBy(Pit::getPlayer, summingInt(Pit::getSeedCount)));
        boolean finished = false;
        for (Player player : seedCountPerPitGroupByPlayer.keySet()) {
            if (seedCountPerPitGroupByPlayer.get(player) == 0) {
                finished = true;
            }
        }
        if (finished) {
            Map<Player, Integer> playerTotalSeedsGroupBy = game.getBoard().getPits()
                    .stream()
                    .collect(groupingBy(Pit::getPlayer, summingInt(Pit::getSeedCount)));
            Player champion = Collections.max(playerTotalSeedsGroupBy.entrySet(), Map.Entry.comparingByValue()).getKey();
            game.setChampion(new Champion(champion, playerTotalSeedsGroupBy.get(champion)));
            log.info("Champion is: " + champion.getUserName() + " With Total Seed Of " + playerTotalSeedsGroupBy.get(champion));
        }
    }
}
