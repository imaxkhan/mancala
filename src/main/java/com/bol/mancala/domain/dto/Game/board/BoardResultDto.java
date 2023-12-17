package com.bol.mancala.domain.dto.Game.board;

import com.bol.mancala.domain.dto.Game.pit.PitResultDto;
import com.bol.mancala.domain.dto.Game.player.PlayerResultDto;
import com.bol.mancala.domain.model.concept.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Response DTO responsible for showing
 * player turn and all pit status regarding seedCount
 */
@Getter
@Setter
@NoArgsConstructor
public class BoardResultDto {
    private PlayerResultDto activePlayer;
    private List<PitResultDto> pits;

    public BoardResultDto(Board board) {
        this.pits = board.getPits()
                .stream()
                .map(PitResultDto::new)
                .collect(Collectors.toList());
        if (board.getActivePlayer() != null) {
            this.activePlayer = new PlayerResultDto(board.getActivePlayer());
        }
    }
}
