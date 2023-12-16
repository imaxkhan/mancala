package com.bol.mancala.presentation.constants;

public abstract class GameRestApi {
    public static final String GAME_SETUP = "/games/setup";
    public static final String PLAYER_RANDOM_PICK = "/players/pick/{gameId}";
    public static final String GAME_PLAY = "/plays/{gameId}";
}
