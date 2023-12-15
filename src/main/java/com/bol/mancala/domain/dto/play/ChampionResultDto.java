package com.bol.mancala.domain.dto.play;

import com.bol.mancala.domain.dto.Game.PlayerDto;
import com.bol.mancala.domain.model.player.Champion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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