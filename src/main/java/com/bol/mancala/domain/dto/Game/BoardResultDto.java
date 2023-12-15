package com.bol.mancala.domain.dto.Game;

import com.bol.mancala.domain.model.concept.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BoardResultDto {
    private List<PitResultDto> pits;
    private UUID activePlayerId;

    public BoardResultDto(Board board) {
        this.pits = board.getPits()
                .stream()
                .map(PitResultDto::new)
                .collect(Collectors.toList());
        this.activePlayerId = board.getActivePlayerId();
    }
}
