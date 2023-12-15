package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import com.bol.mancala.game.rule.basic.GameCommander;

import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class ChampionCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) {
        checkChampion(game);
    }

    private void checkChampion(Game game) {
        Map<Player, Integer> seedCountPerPitGroupByPlayer = game.getBoard().getPits()
                .stream()
                .filter(pit -> !pit.isStore())
                .collect(groupingBy(Pit::getPlayer, summingInt(Pit::getSeedCount)));
        Player champion = null;
        for (Player player : seedCountPerPitGroupByPlayer.keySet()) {
            if (seedCountPerPitGroupByPlayer.get(player) == 0) {
                champion = player;
            }
        }
        if (champion != null) {
            int totalSeeds = game.getBoard().getPits().stream()
                    .map(Pit::getSeedCount).mapToInt(Integer::intValue).sum();
            game.setChampion(new Champion(champion, totalSeeds));
        }
    }
}
