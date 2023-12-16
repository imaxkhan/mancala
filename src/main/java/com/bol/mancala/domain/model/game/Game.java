package com.bol.mancala.domain.model.game;

import com.bol.mancala.domain.enums.GameStatus;
import com.bol.mancala.domain.model.concept.Board;
import com.bol.mancala.domain.model.player.Champion;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract overview of game
 */
@Getter
@Setter
@NoArgsConstructor
public class Game {
    //unique id of game
    private UUID gameId;
    //list of players in game
    private List<Player> players;
    //board with pits and seeds and stores
    private Board board;
    //all game settings
    private GameSetting gameSetting;
    //who is the winner
    private Champion champion;
    //status of game, running,finished,..
    private GameStatus status;

    public Game(GameSetting gameSetting, List<Player> players) {
        this.gameId = UUID.randomUUID();
        this.players = players;
        this.gameSetting = gameSetting;
        this.status = GameStatus.NOT_STARTED;
    }

    //to make game unique with game id
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
