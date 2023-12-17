package com.bol.mancala.domain.dto.play.action;

import com.bol.mancala.domain.dto.Game.board.BoardResultDto;
import com.bol.mancala.domain.dto.Game.player.PlayerResultDto;
import com.bol.mancala.domain.dto.play.champion.ChampionResultDto;
import com.bol.mancala.domain.model.game.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Response DTO for handling result after each player movement
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayResultDto {
    private PlayerResultDto nextPlayer;
    private ChampionResultDto champion;
    private BoardResultDto boardOverview;

    public PlayResultDto(Game game) {
        if (game != null) {
            if (game.getBoard().getActivePlayer() != null) {
                this.nextPlayer = new PlayerResultDto(game.getBoard().getActivePlayer());
            }
            if (game.getChampion() != null) {
                this.champion = new ChampionResultDto(game.getChampion());
            }
            this.boardOverview = new BoardResultDto(game.getBoard());
        }
    }
}
