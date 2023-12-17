package com.bol.mancala.domain.dto.play.champion;

import com.bol.mancala.domain.dto.Game.player.PlayerDto;
import com.bol.mancala.domain.model.player.Champion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Response mediator DTO for showing who is the winner of game
 */
@Getter
@Setter
@NoArgsConstructor
public class ChampionResultDto {
    private PlayerDto champion;
    private int totalSeeds;

    public ChampionResultDto(Champion champion) {
        if (champion != null) {
            this.champion = new PlayerDto(champion.getPlayer());
            this.totalSeeds = champion.getTotalSeed();
        }
    }
}
