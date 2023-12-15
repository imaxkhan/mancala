package com.bol.mancala.game.rule.impl;

import com.bol.mancala.domain.dto.play.PlayDto;
import com.bol.mancala.domain.model.concept.Pit;
import com.bol.mancala.domain.model.game.Game;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.game.rule.basic.GameCommander;

import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class ChampionCommander implements GameCommander {

    @Override
    public void play(Game game, PlayDto playDto) {
        checkChampion(game);
    }

    private void checkChampion(Game game) {
        Map<UUID, Integer> seedCountPerPitGroupByPlayerId = game.getBoard().getPits()
                .stream()
                .collect(groupingBy(Pit::getPlayerId, summingInt(Pit::getSeedCount)));
        UUID championPlayerId = null;
        for (java.util.UUID UUID : seedCountPerPitGroupByPlayerId.keySet()) {
            if (seedCountPerPitGroupByPlayerId.get(UUID) == 0) {
                championPlayerId = UUID;
            }
        }
        if(championPlayerId!=null){
            game.setChampion(new Champion(championPlayerId));
        }
    }
}
