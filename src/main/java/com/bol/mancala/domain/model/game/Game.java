package com.bol.mancala.domain.model.game;

import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Game {
    private UUID gameId;
    private List<Player> players;
    private GameSetting gameSetting;
    private Champion champion;
    private GameStatus status;

    public Game(GameSetting gameSetting, List<Player> players) {
        this.gameId = UUID.randomUUID();
        this.players = players;
        this.gameSetting = gameSetting;
        this.status = GameStatus.NOT_STARTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }
}
